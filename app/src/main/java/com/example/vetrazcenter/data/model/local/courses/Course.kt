package com.example.vetrazcenter.data.model.local.courses

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "course_table")
data class Course(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val locationInfo: LocationInfo?,
    val department: String?,
    val contactPhone: String?,
    val courseName: String?,
    val description: String?,
    val imageUrl: String?,
    val paymentTerm: String?,
    val teacherName: String?,
    val studentsAge: StudentsAge?,
    val schedule: List<Schedule>?,
    val program: String?,
    val programDuration: String?,
    val recruitingIsOpen: Boolean?
) : Serializable

