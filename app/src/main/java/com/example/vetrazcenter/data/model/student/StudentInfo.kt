package com.example.vetrazcenter.data.model.student

import java.util.*

data class StudentInfo(
    var name: String? = null,
    var surname: String? = null,
    var courseCategory: String? = null,
    var contactPhone: String? = null,
    var courseName: String? = null,
    var email: String? = null,
    var birthday: Date? = null,
    var address: String? = null,
    var schoolInfo: School?
)
