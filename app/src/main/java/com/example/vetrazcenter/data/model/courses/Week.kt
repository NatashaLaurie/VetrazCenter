package com.example.vetrazcenter.data.model.courses

import android.os.Parcelable
import com.example.vetrazcenter.data.model.courses.Lesson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Week(
    var lessons: List<Lesson>? = null
) : Parcelable
