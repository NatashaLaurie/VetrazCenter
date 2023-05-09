package com.example.vetrazcenter.presentation.courses.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.vetrazcenter.R
import com.example.vetrazcenter.databinding.FragmentCourseBinding
import com.google.android.material.tabs.TabLayoutMediator


class CourseFragment : Fragment() {
    private var _binding: FragmentCourseBinding? = null

    private val binding get() = _binding!!
    private val args: CourseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCourseBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabNames = resources.getStringArray(R.array.courses_tabs)
        binding.viewpager.adapter =
            ViewPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle, args.course)

        binding.viewpager.setPageTransformer(ZoomOutPageTransformer())

        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

        val course = args.course

        Glide.with(this).load(course.imageUrl).into(binding.toolbarImage)
        binding.title.text = course.courseName
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}