package com.example.vetrazcenter.data.remote.model

import com.example.vetrazcenter.data.local.model.LocationInfo
import com.google.firebase.firestore.PropertyName

data class LocationInfoDto(
    var address: String? = null,

    @get:PropertyName("contact_phone")
    @set:PropertyName("contact_phone")
    var contactPhone: String? = null,

    @get:PropertyName("room_number")
    @set:PropertyName("room_number")
    var roomNumber: String? = null,
) {
    fun toDomainObject() =
        LocationInfo(
            address = address,
            contactPhone = contactPhone,
            roomNumber = roomNumber
        )
}
