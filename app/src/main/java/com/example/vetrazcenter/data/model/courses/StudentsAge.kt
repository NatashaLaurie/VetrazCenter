package com.example.vetrazcenter.data.model.courses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentsAge(
    var from: Int? =  0,
    var to: Int? =  0
) : Parcelable
