package com.example.vetrazcenter.presentation.courses.course

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android. view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vetrazcenter.R
import com.example.vetrazcenter.core.Constants
import com.example.vetrazcenter.core.Utils.parcelable
import com.example.vetrazcenter.data.model.courses.Course
import com.example.vetrazcenter.databinding.FragmentCourseScheduleBinding


class CourseScheduleFragment : Fragment() {

    private var _binding: FragmentCourseScheduleBinding? = null

    private val binding get() = _binding!!
    private lateinit var scheduleAdapter: ScheduleAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCourseScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val course = arguments?.parcelable<Course>(Constants.COURSE_KEY)

        binding.apply {
            val locationInfo = course?.locationInfo
            tvAddress.text = locationInfo?.address.orEmpty()
            tvContactPhone.text = locationInfo?.contactPhone.orEmpty()
            tvRoom.text = locationInfo?.roomNumber?.let {
                context?.getString(R.string.room_number, it)
            } ?: ""
        }

        scheduleAdapter = ScheduleAdapter()
        binding.rvSchedule.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false )
            adapter = scheduleAdapter
        }

        val schedules = course?.schedule

        if (schedules != null) {
            binding.tvComingSoon.visibility = View.GONE
            scheduleAdapter.submitList(schedules)
        } else {
            binding.daysLayout.visibility = View.GONE
            binding.tvComingSoon.visibility = View.VISIBLE
            binding.divider.visibility = View.GONE
        }

        val number: String = binding.tvContactPhone.text.toString()
        binding.tvContactPhone.setOnClickListener {
            if (number.trim { it <= ' ' }.isNotEmpty()) {
                call(number)
            }
        }


    }

    private fun call(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}