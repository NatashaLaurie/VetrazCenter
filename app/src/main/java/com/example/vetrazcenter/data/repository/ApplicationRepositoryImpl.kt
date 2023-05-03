package com.example.vetrazcenter.data.repository

import com.example.vetrazcenter.data.model.student.StudentInfo
import com.example.vetrazcenter.domain.repository.AddApplicationResponse
import com.example.vetrazcenter.domain.repository.ApplicationRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
    private val database: FirebaseFirestore
) : ApplicationRepository {
    override suspend fun apply(studentInfo: StudentInfo): AddApplicationResponse {
        TODO("Not yet implemented")
    }
}