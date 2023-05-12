package com.example.vetrazcenter.data.repository

import com.example.vetrazcenter.data.model.student.StudentInfo
import com.example.vetrazcenter.domain.model.Response
import com.example.vetrazcenter.domain.repository.AddApplicationResponse
import com.example.vetrazcenter.domain.repository.ApplicationRepository
import com.example.vetrazcenter.utils.Constants.APPLICATIONS
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
    private val database: FirebaseFirestore
) : ApplicationRepository {
    override suspend fun apply(studentInfo: StudentInfo): AddApplicationResponse = try {
        val id = "${studentInfo.name} ${studentInfo.surname}"
        database.collection(APPLICATIONS).document(id).set(studentInfo).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }
}