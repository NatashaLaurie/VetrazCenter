package com.example.vetrazcenter.ui.onboarding

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.vetrazcenter.R
import com.example.vetrazcenter.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val viewModel: OnBoardingViewModel by activityViewModels()
    private var animationHandler = Handler(Looper.getMainLooper())
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val root: View = binding.root


        startPulse()
        binding.buttonNext.setOnClickListener {
            animationHandler.removeCallbacks(animationRunnable)
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

    private fun startPulse() {
        animationRunnable.run()
    }

    private var animationRunnable = object : Runnable {
        override fun run() {
            val animatedButtonImage1 = binding.buttonImageAnim1
            val animatedButtonImage2 = binding.buttonImageAnim2
            animatedButtonImage1.animate()
                .scaleX(4f).scaleY(4f).alpha(0f).setDuration(1000).withEndAction {
                    animatedButtonImage1.scaleX = 1f
                    animatedButtonImage1.scaleY = 1f
                    animatedButtonImage1.alpha = 1f

                }
            animatedButtonImage2.animate()
                .scaleX(4f).scaleY(4f).alpha(0f).setDuration(700).withEndAction {
                    animatedButtonImage2.scaleX = 1f
                    animatedButtonImage2.scaleY = 1f
                    animatedButtonImage2.alpha = 1f

                }
            animationHandler.postDelayed(this, 1500)
        }

    }

    override fun onPause() {
        animationHandler.removeCallbacks(animationRunnable)
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
