package com.example.vetrazcenter.presentation.courses.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vetrazcenter.data.local.model.Course
import com.example.vetrazcenter.data.remote.model.student.StudentInfo
import com.example.vetrazcenter.data.repository.SavedCoursesRepositoryImpl
import com.example.vetrazcenter.domain.model.Response.Loading
import com.example.vetrazcenter.domain.repository.AddApplicationResponse
import com.example.vetrazcenter.domain.use_case.CourseApplicationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val savedCoursesRepositoryImpl: SavedCoursesRepositoryImpl,
    private val useCases: CourseApplicationUseCase
) : ViewModel() {


    private val _addApplicationResponse = MutableStateFlow<AddApplicationResponse>(Loading)
    var addApplicationResponse = _addApplicationResponse.asStateFlow()


    fun apply(studentInfo: StudentInfo) = viewModelScope.launch {
            _addApplicationResponse.value = useCases.addApplication(studentInfo)
    }


    fun insert(course: Course) = viewModelScope.launch {
        savedCoursesRepositoryImpl.insert(course)
    }
}