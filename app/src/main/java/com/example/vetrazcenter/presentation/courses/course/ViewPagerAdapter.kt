package com.example.vetrazcenter.presentation.courses.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vetrazcenter.utils.Constants.COURSE_KEY
import com.example.vetrazcenter.data.local.model.Course


private const val NUM_TABS = 2

class ViewPagerAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle,
    private var course: Course
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = NUM_TABS

    private var bundle = Bundle()
    private val courseDescriptionFragment = CourseDescriptionFragment()
    private val courseScheduleFragment = CourseScheduleFragment()

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                bundle.putSerializable(COURSE_KEY, course)
                courseDescriptionFragment.arguments = bundle

                return courseDescriptionFragment
            }
            1 -> {
                courseScheduleFragment.arguments = bundle
                return courseScheduleFragment
            }
        }
        return courseDescriptionFragment

    }
}