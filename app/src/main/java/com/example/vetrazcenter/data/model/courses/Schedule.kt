package com.example.vetrazcenter.data.model.courses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedule(
    var groupName: String? = null,
    var week: List<Week>? = null,
) : Parcelable