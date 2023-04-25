package com.example.vetrazcenter.domain.repository

import com.example.vetraz.data.model.student.StudentInfo
import com.example.vetrazcenter.data.model.courses.Course
import com.example.vetrazcenter.utils.UiState
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
 fun getCoursesList(): Flow<UiState<List<Course>>>
 fun apply(studentInfo: StudentInfo, result: (UiState<String>) -> Unit)
}