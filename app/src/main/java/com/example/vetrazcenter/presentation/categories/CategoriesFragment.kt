package com.example.vetrazcenter.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vetrazcenter.R
import com.example.vetrazcenter.databinding.FragmentCategoryBinding
import com.example.vetrazcenter.domain.model.Response
import com.example.vetrazcenter.presentation.onboarding.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var courseAdapter: OngoingCoursesAdapter


    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBoardingViewModel.fetchOnBoarding().observe(requireActivity()) {
            if (it == false) {
                view?.findNavController()
                    ?.navigate(R.id.action_nav_home_to_onBoardingFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                categoryViewModel.coursesResponse.collect {response ->
                    when(response) {
                        is Response.Loading -> {
                            binding.shimmerView.startShimmer()
                        }
                        is Response.Failure -> {
                            binding.shimmerView.stopShimmer()
                            binding.shimmerView.isVisible = false
                        }
                        is Response.Success -> {
                            response.data.let { courses ->
                                courseAdapter.differ.submitList(courses.toList())
                            }
                            binding.shimmerView.stopShimmer()
                            binding.shimmerView.isVisible = false
                        }
                    }
                }
            }
        }
    }
    private fun setupRecyclerView() {
        courseAdapter = OngoingCoursesAdapter()
        binding.rvOngoingCourses.apply {
            adapter = courseAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}