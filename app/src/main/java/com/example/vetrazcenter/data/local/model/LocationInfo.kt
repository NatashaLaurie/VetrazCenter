package com.example.vetrazcenter.data.local.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LocationInfo(
    @SerializedName("address")
    val address: String?,
    @SerializedName("contactPhone")
    val contactPhone: String?,
    @SerializedName("roomNumber")
    val roomNumber: String?,
) : Serializable