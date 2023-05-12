package com.example.vetrazcenter.data.model.student

import java.io.Serializable


data class StudentInfo(
    var name: String? = null,
    var surname: String? = null,
    var contactPhone: String? = null,
    var courseName: String? = null,
    var email: String? = null,
    var birthday: String? = null,
    var address: String? = null,
    var schoolName: String? = null
): Serializable
