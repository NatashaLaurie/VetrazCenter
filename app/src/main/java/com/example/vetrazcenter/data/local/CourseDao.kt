package com.example.vetrazcenter.data.local

import androidx.room.*
import com.example.vetrazcenter.data.local.model.Course
import kotlinx.coroutines.flow.Flow


@Dao
interface CourseDao {

    @Query("SELECT * FROM course_table")
    fun getAll(): Flow<List<Course>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: Course)

    @Delete
    suspend fun delete(course: Course)

}