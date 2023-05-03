package com.example.vetrazcenter.data.model.courses

import com.google.firebase.firestore.PropertyName

data class Schedule(
    @get:PropertyName("group_name")
    @set:PropertyName("group_name")
    var groupName: String? = null,

    @get:PropertyName("weekly_schedule")
    @set:PropertyName("weekly_schedule")
    var week: Week? = null,
)