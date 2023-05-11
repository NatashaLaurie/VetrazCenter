package com.example.vetrazcenter.data.model.local.courses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StudentsAge(
    @SerializedName("from")
    val from: Int?,
    @SerializedName("to")
    val to: Int?
) : Serializable