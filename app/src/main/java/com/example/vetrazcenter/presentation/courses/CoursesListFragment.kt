package com.example.vetrazcenter.presentation.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.vetrazcenter.R
import com.example.vetrazcenter.databinding.FragmentCoursesListBinding

class CoursesListFragment : Fragment() {

    private var _binding: FragmentCoursesListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCoursesListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonCheck.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_coursesListFragment_to_courseFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}