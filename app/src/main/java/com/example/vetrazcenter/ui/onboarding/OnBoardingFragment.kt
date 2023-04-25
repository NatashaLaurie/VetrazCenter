package com.example.vetrazcenter.ui.onboarding

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.vetrazcenter.R
import com.example.vetrazcenter.databinding.FragmentOnBoardingBinding
import com.example.vetrazcenter.utils.SystemUIHandler
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val viewModel: OnBoardingViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonNext.setOnClickListener {
            viewModel.saveOnBoarding(true)
            view?.findNavController()
                ?.navigate(R.id.action_onBoardingFragment_to_nav_home)
        }

        val animationDrawable = binding.onBoardingMainLayout.background as AnimationDrawable
        animationDrawable.apply {
            setEnterFadeDuration(2500)
            setExitFadeDuration(5000)
            start()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}