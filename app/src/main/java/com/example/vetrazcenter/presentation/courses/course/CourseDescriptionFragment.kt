package com.example.vetrazcenter.presentation.courses.course

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vetrazcenter.R
import com.example.vetrazcenter.utils.Constants.COURSE_KEY
import com.example.vetrazcenter.utils.Utils.serializable
import com.example.vetrazcenter.databinding.FragmentCourseDescriptionBinding
import com.example.vetrazcenter.data.local.model.Course


class CourseDescriptionFragment : Fragment() {

    private var _binding: FragmentCourseDescriptionBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCourseDescriptionBinding.inflate(inflater, container, false)

        val course = arguments?.serializable<Course>(COURSE_KEY)

        binding.apply {
            if (course != null) {
                tvDescription.text = course.description
                tvDepartment.text = course.department
                tvPaymentTerm.text = course.paymentTerm
                tvProgram.text = course.program
                tvProgramDuration.text = course.programDuration
                tvPhone.text = course.contactPhone
                tvTeacher.text = course.teacherName
                tvAge.text = context?.getString(
                    R.string.age,
                    course.studentsAgeFrom.toString(),
                    course.studentsAgeTo.toString()
                ) ?: ""
            }
        }

        val number: String = binding.tvPhone.text.toString()
        binding.tvPhone.setOnClickListener {
            if (number.trim { it <= ' ' }.isNotEmpty()) {
                call(number)
            }
        }

        return binding.root
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