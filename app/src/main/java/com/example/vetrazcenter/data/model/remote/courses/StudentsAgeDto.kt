package com.example.vetrazcenter.data.model.remote.courses

import com.example.vetrazcenter.data.model.local.courses.StudentsAge

data class StudentsAgeDto(
    var from: Int? = 0,
    var to: Int? = 0
) {
    fun toDomainObject() =
        StudentsAge(
            from = from,
            to = to
        )
}
