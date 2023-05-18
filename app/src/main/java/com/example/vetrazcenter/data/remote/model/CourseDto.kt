package com.example.vetrazcenter.data.remote.model

import com.example.vetrazcenter.data.local.model.Course
import com.example.vetrazcenter.data.local.model.Schedule
import com.google.firebase.firestore.PropertyName

data class CourseDto(
    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "id",

    @get:PropertyName("location_info")
    @set:PropertyName("location_info")
    var locationInfoDto: LocationInfoDto? = null,

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

    @get:PropertyName("student_age_from")
    @set:PropertyName("student_age_from")
    var studentsAgeFrom: Int? = null,

    @get:PropertyName("student_age_to")
    @set:PropertyName("student_age_to")
    var studentsAgeTo: Int? = null,

    @get:PropertyName("groups_schedule")
    @set:PropertyName("groups_schedule")
    var scheduleDto: List<ScheduleDto>? = null,

    @get:PropertyName("program")
    @set:PropertyName("program")
    var program: String? = null,

    @get:PropertyName("program_duration")
    @set:PropertyName("program_duration")
    var programDuration: String? = null,

    @get:PropertyName("recruiting_is_open")
    @set:PropertyName("recruiting_is_open")
    var recruitingIsOpen: Boolean? = null,
) {
    fun toDomainObject() =
        Course(
            id = id,
            locationInfo = locationInfoDto?.toDomainObject(),
            department = department,
            contactPhone = contactPhone,
            courseName = courseName,
            description = description,
            imageUrl = imageUrl,
            paymentTerm = paymentTerm,
            teacherName = teacherName,
            studentsAgeFrom = studentsAgeFrom,
            studentsAgeTo = studentsAgeTo,
            schedule = scheduleToScheduleDto(scheduleDto),
            program = program,
            programDuration = programDuration,
            recruitingIsOpen = recruitingIsOpen,
        )

    private fun scheduleToScheduleDto(scheduleDto: List<ScheduleDto>?): List<Schedule>? {
        return scheduleDto?.map { it.toDomainObject() }
    }
}
