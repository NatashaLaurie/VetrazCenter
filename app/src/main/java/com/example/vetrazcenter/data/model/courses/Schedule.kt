package com.example.vetrazcenter.data.model.courses

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize

data class Schedule(
    @get:PropertyName("group_name")
    @set:PropertyName("group_name")
    var groupName: String? = null,

    @get:PropertyName("weekly_schedule")
    @set:PropertyName("weekly_schedule")
    var week: Week? = null,
) : Parcelable