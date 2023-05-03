package com.example.vetrazcenter.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.vetrazcenter.data.common.OnBoardingPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val preference: OnBoardingPrefs) :
    ViewModel() {
    fun saveOnBoarding(save: Boolean) {
        viewModelScope.launch {
            preference.saveOnBoarding(save)
        }
    }

    fun fetchOnBoarding() = preference.fetchOnBoarding().asLiveData()
}