package com.example.vetrazcenter.data.repository

import com.example.vetrazcenter.data.local.CourseDao
import com.example.vetrazcenter.data.local.model.Course
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavedCoursesRepositoryImpl @Inject constructor(
    private val courseDao: CourseDao
) {

    fun getAll(): Flow<List<Course>> =
        courseDao.getAll()


    suspend fun insert(course: Course) {
        courseDao.insert(course)
    }

    suspend fun delete(course: Course) {
        courseDao.delete(course)
    }
}