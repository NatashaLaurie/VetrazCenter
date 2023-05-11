package com.example.vetrazcenter.data.model.local.courses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Week(
    @SerializedName("mondayLessons")
    val mondayLessons: List<String>?,
    @SerializedName("tuesdayLessons")
    val tuesdayLessons: List<String>?,
    @SerializedName("wednesdayLessons")
    val wednesdayLessons: List<String>?,
    @SerializedName("thursdayLessons")
    val thursdayLessons: List<String>?,
    @SerializedName("fridayLessons")
    val fridayLessons: List<String>?,
    @SerializedName("saturdayLessons")
    val saturdayLessons: List<String>?,
    @SerializedName("sundayLessons")
    val sundayLessons: List<String>?
) : Serializable
