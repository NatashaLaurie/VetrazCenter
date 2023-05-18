package com.example.vetrazcenter.data.local.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Schedule(
    @SerializedName("groupName")
    val groupName: String?,
    @SerializedName("weekDto")
    val week: Week?,
) : Serializable