package com.example.vetrazcenter.presentation.courses.courses_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vetrazcenter.domain.model.Response
import com.example.vetrazcenter.domain.repository.CoursesResponse
import com.example.vetrazcenter.domain.use_case.CoursesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesListViewModel @Inject constructor(
    private val useCases: CoursesUseCases
) : ViewModel() {


    private val _coursesResponse = MutableStateFlow<CoursesResponse>(Response.Loading)
    val coursesResponse = _coursesResponse.asStateFlow()


    fun getCoursesByCategory(category: String) = viewModelScope.launch {
        useCases.getCoursesByCategory(category).collect { response ->
            _coursesResponse.value = response
        }
    }

    fun getOngoingCourses() = viewModelScope.launch {
        useCases.getOngoingCourses().collect { response ->
            _coursesResponse.value = response
        }
    }
}