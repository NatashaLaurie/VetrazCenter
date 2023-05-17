package com.example.vetrazcenter.presentation.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vetrazcenter.R
import com.example.vetrazcenter.databinding.FragmentSavedCoursesBinding
import com.example.vetrazcenter.presentation.courses.courses_list.CoursesAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedCoursesFragment : Fragment() {

    private var _binding: FragmentSavedCoursesBinding? = null
    private val binding get() = _binding!!

    private val savedViewModel: SavedCoursesViewModel by viewModels()
    private lateinit var courseAdapter: CoursesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSavedCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeCoursesList()

        handleCourseClick()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val course = courseAdapter.differ.currentList[position]
                savedViewModel.deleteCourse(course)
                unSubscribeTopic(course.id)
                Snackbar.make(
                    view,
                    context?.getString(R.string.course_deleted).toString(),
                    Snackbar.LENGTH_LONG
                ).apply {
                    setAction(context.getString(R.string.undo)) {
                        savedViewModel.insertCourse(course)
                        subscribeTopic(course.id)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvCourses)
        }

    }

    private fun handleCourseClick() {
        courseAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("course", it)
                putString("courseName", it.courseName)
            }
            findNavController().navigate(
                R.id.action_nav_saved_to_courseFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView() {
        courseAdapter = CoursesAdapter()
        binding.rvCourses.apply {
            adapter = courseAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }

    private fun observeCoursesList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                savedViewModel.coursesResponse.collect { response ->
                    courseAdapter.differ.submitList(response.toList())
                }
            }
        }
    }

    private fun subscribeTopic(courseId: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(courseId)
    }

    private fun unSubscribeTopic(courseId: String) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(courseId)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}