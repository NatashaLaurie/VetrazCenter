package com.example.vetrazcenter.data.model.courses

import com.google.firebase.firestore.PropertyName

data class LocationInfo(
    var address: String? = null,

    @get:PropertyName("contact_phone")
    @set:PropertyName("contact_phone")
    var contactPhone: String? = null,

    @get:PropertyName("room_number")
    @set:PropertyName("room_number")
    var roomNumber: String? = null,
)
