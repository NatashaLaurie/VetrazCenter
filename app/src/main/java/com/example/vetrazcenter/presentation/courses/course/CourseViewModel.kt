package com.example.vetrazcenter.presentation.courses.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vetrazcenter.data.repository.SavedCoursesRepositoryImpl
import com.example.vetrazcenter.data.model.local.courses.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val savedCoursesRepositoryImpl: SavedCoursesRepositoryImpl
) : ViewModel() {

    fun insert(course: Course) = viewModelScope.launch {
        savedCoursesRepositoryImpl.insert(course)
    }
}