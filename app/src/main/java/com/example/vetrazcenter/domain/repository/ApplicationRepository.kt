package com.example.vetrazcenter.domain.repository

import com.example.vetrazcenter.data.remote.model.student.StudentInfo
import com.example.vetrazcenter.domain.model.Response

typealias AddApplicationResponse = Response<Boolean>

interface ApplicationRepository {

    suspend fun apply(studentInfo: StudentInfo): AddApplicationResponse
}

