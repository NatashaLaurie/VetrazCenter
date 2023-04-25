package com.example.vetrazcenter.data.model.courses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lesson(
    var from: String? = null,
    var to: String? = null
) : Parcelable
