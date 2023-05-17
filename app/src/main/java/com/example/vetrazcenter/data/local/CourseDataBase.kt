package com.example.vetrazcenter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vetrazcenter.data.model.local.courses.Course

@Database(
    entities = [Course::class],
    version = 3
)
@TypeConverters(Converters::class)
abstract class CourseDataBase : RoomDatabase() {
    abstract fun getCourseDao(): CourseDao

}

