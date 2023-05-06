package com.example.vetrazcenter.presentation.courses.course

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


private const val NUM_TABS = 2

class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return CourseDescriptionFragment()
            1 -> return CourseScheduleFragment()
        }
        return CourseDescriptionFragment()

    }
}