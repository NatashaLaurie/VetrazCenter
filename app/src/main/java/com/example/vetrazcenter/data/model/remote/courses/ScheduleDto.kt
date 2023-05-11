package com.example.vetrazcenter.data.model.remote.courses

import com.example.vetrazcenter.data.model.local.courses.Schedule
import com.google.firebase.firestore.PropertyName

data class ScheduleDto(
    @get:PropertyName("group_name")
    @set:PropertyName("group_name")
    var groupName: String? = null,

    @get:PropertyName("weekly_schedule")
    @set:PropertyName("weekly_schedule")
    var weekDto: WeekDto? = null,
) {
    fun toDomainObject() =
        Schedule(
            groupName = groupName,
            week = weekDto?.toDomainObject()
        )
}