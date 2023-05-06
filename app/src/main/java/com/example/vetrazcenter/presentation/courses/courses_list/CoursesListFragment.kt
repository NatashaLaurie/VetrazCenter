package com.example.vetrazcenter.presentation.courses.courses_list

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vetrazcenter.R
import com.example.vetrazcenter.databinding.FragmentCoursesListBinding
import com.example.vetrazcenter.domain.model.Response
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoursesListFragment : Fragment() {

    private var _binding: FragmentCoursesListBinding? = null

    private val binding get() = _binding!!
    private val coursesViewModel: CoursesListViewModel by viewModels()
    private lateinit var courseAdapter: CoursesAdapter
    private val args: CoursesListFragmentArgs by navArgs()

    private val menuHost: MenuHost get() = requireActivity()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCoursesListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        args.categoryId?.let {
            coursesViewModel.getCoursesByCategory(it)
        }
        if (args.categoryId.isNullOrEmpty()) {
            coursesViewModel.getOngoingCourses()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        setupCoursesRecyclerView()
        observeCoursesList()
    }

    private fun observeCoursesList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                coursesViewModel.coursesResponse.collect { response ->
                    when (response) {
                        is Response.Loading -> {
                            binding.shimmerView.startShimmer()
                        }
                        is Response.Failure -> {
                            binding.shimmerView.stopShimmer()
                            binding.shimmerView.isVisible = false
                        }
                        is Response.Success -> {
                            binding.shimmerView.stopShimmer()
                            binding.shimmerView.isVisible = false

                            response.data.let { courses ->
                                courseAdapter.differ.submitList(courses.toList())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupMenu() {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {

            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Надуваем fragment_menu и мержим с прошлым menu
                menuInflater.inflate(R.menu.search_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Пользователь кликнул на элемент меню
                // return true — не нужно передавать нажатие другому провайдеру
                // return false — передаем нажатие следующему провайдеру
                return false
            }

            override fun onMenuClosed(menu: Menu) {

            }

        }, viewLifecycleOwner)
    }

    private fun setupCoursesRecyclerView() {
        courseAdapter = CoursesAdapter()
        binding.rvCourses.apply {
            adapter = courseAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}