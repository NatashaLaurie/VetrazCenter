package com.example.vetrazcenter.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.vetrazcenter.R
import com.example.vetrazcenter.databinding.FragmentCategoryBinding
import com.example.vetrazcenter.ui.onboarding.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.

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
        val galleryViewModel =
            ViewModelProvider(this).get(CategoryViewModel::class.java)

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonCheck.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_nav_home_to_coursesListFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}