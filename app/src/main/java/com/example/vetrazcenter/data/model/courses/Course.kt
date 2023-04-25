package com.example.vetrazcenter.data.model.courses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    var id: String? = null,
    var address: String? = null,
    var category: String? = null,
    var contactPhone: String? = null,
    var courseName: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var paymentTerm: String? = null,
    var teacherName: String? = null,
    var studentsAge: StudentsAge? = null,
    var schedule: List<Schedule>? = null
) : Parcelable
