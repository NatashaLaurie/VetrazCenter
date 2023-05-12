package com.example.vetrazcenter.domain.repository

import com.example.vetrazcenter.domain.model.Response
import com.example.vetrazcenter.data.model.local.courses.Course
import kotlinx.coroutines.flow.Flow

typealias Courses = List<Course>
typealias CoursesResponse = Response<Courses>

interface CoursesRepository {
 fun getCoursesListByCategory(category: String): Flow<CoursesResponse>

 fun getOngoingCoursesList(): Flow<CoursesResponse>

}


