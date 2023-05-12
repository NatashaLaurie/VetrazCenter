package com.example.vetrazcenter.presentation.courses.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.vetrazcenter.R
import com.example.vetrazcenter.data.model.local.courses.Course
import com.example.vetrazcenter.data.model.student.StudentInfo
import com.example.vetrazcenter.databinding.FragmentCourseBinding
import com.example.vetrazcenter.domain.model.Response
import com.example.vetrazcenter.utils.Utils.serializable
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CourseFragment : Fragment() {
    private var _binding: FragmentCourseBinding? = null

    private val binding get() = _binding!!
    private val args: CourseFragmentArgs by navArgs()
    private val courseViewModel: CourseViewModel by viewModels()

    lateinit var course: Course

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        course = args.course
        setFragmentResultListener("request_key") { key, bundle ->
            val studentInfo = bundle.serializable<StudentInfo>("extra_key")
            studentInfo?.courseName = course.courseName
            if (studentInfo != null) {
                courseViewModel.apply(studentInfo)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        setupApplyAvailability()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeApplicationStatus()

        val tabNames = resources.getStringArray(R.array.courses_tabs)
        binding.viewpager.adapter =
            ViewPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle, args.course)

        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

        Glide.with(this).load(course.imageUrl).into(binding.toolbarImage)
        binding.title.text = course.courseName
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.fabSave.setOnClickListener {
            courseViewModel.insert(course)
            Snackbar.make(
                view,
                context?.getString(R.string.course_saved).toString(),
                Snackbar.LENGTH_SHORT
            ).show()
        }


    }

    private fun setupApplyAvailability() {
        if (course.recruitingIsOpen == false) {
            binding.fabApply.setOnClickListener {
                view?.let {
                    Snackbar.make(
                        it,
                        context?.getString(R.string.recruiting_closed).toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

        } else {
            binding.fabApply.setOnClickListener {
                openApplyBottomSheet()
            }
        }
    }

    private fun observeApplicationStatus() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                courseViewModel.addApplicationResponse.collect { response ->
                    when (response) {
                        is Response.Loading -> {}
                        is Response.Failure -> {
                            context?.let {
                                showSnackBar(
                                    requireView(),
                                    it.getString(R.string.application_failed)
                                )
                            }
                        }
                        is Response.Success -> {
                            context?.let {
                                showSnackBar(
                                    requireView(),
                                    it.getString(R.string.application_accepted)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showSnackBar(view: View, msg: String) {
        Snackbar.make(
            view,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun openApplyBottomSheet() {
        val bottomFragment = ApplyDialogFragment()
        bottomFragment.show(parentFragmentManager, BOTTOM_SHEET_APPLY_TAG)
    }

    companion object {
        private const val BOTTOM_SHEET_APPLY_TAG = "bottom_sheet_apply"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}