package com.example.vetrazcenter.data.remote.model

import com.example.vetrazcenter.data.local.model.Schedule
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