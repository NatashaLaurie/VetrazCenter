package com.example.vetrazcenter.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vetrazcenter.data.local.model.Course
import com.example.vetrazcenter.data.repository.SavedCoursesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedCoursesViewModel @Inject constructor(
    private val savedCoursesRepositoryImpl: SavedCoursesRepositoryImpl
) : ViewModel() {


    private val _coursesResponse = MutableStateFlow(emptyList<Course>())
    val coursesResponse = _coursesResponse.asStateFlow()

    init {
        getCourses()
    }

    private fun getCourses() = viewModelScope.launch {
        savedCoursesRepositoryImpl.getAll().flowOn(Dispatchers.IO)
            .collect { courses: List<Course> ->
                _coursesResponse.update { courses }
            }
    }

    fun deleteCourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) { //this: CoroutineScope
            savedCoursesRepositoryImpl.delete(course)
        }
    }

    fun insertCourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            savedCoursesRepositoryImpl.insert(course)
        }
    }
}