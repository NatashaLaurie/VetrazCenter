package com.example.vetrazcenter.data.model.courses

import com.google.firebase.firestore.PropertyName

data class Course(
    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String? = null,

    @get:PropertyName("location_info")
    @set:PropertyName("location_info")
    var locationInfo: LocationInfo? = null,

    @get:PropertyName("department")
    @set:PropertyName("department")
    var department: String? = null,

    @get:PropertyName("contact_phone")
    @set:PropertyName("contact_phone")
    var contactPhone: String? = null,

    @get:PropertyName("name")
    @set:PropertyName("name")
    var courseName: String? = null,

    @get:PropertyName("description")
    @set:PropertyName("description")
    var description: String? = null,

    @get:PropertyName("image_url")
    @set:PropertyName("image_url")
    var imageUrl: String? = null,

    @get:PropertyName("payment_term")
    @set:PropertyName("payment_term")
    var paymentTerm: String? = null,

    @get:PropertyName("teacher_name")
    @set:PropertyName("teacher_name")
    var teacherName: String? = null,

    @get:PropertyName("students_age")
    @set:PropertyName("students_age")
    var studentsAge: StudentsAge? = null,

    @get:PropertyName("groups_schedule")
    @set:PropertyName("groups_schedule")
    var schedule: List<Schedule>? = null,

    @get:PropertyName("program")
    @set:PropertyName("program")
    var program: String? = null,

    @get:PropertyName("program_duration")
    @set:PropertyName("program_duration")
    var programDuration: String? = null,

    @get:PropertyName("recruiting_is_open")
    @set:PropertyName("recruiting_is_open")
    var recruitingIsOpen: Boolean? = null,
)
