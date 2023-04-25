package com.example.vetrazcenter.data.repository

import android.util.Log
import com.example.vetraz.data.model.student.StudentInfo
import com.example.vetrazcenter.data.model.courses.Course
import com.example.vetrazcenter.data.model.courses.CoursesDocument
import com.example.vetrazcenter.domain.repository.CoursesRepository
import com.example.vetrazcenter.utils.UiState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CoursesRepositoryImpl(
    private val database: FirebaseFirestore
) : CoursesRepository {

    private val TAG = "getCourses"
    override  fun getCoursesList(): Flow<UiState<List<Course>>> {
        return callbackFlow {
            val listenerRegistration =
                database.collection("courses").document("courses")
                    .addSnapshotListener { snapshot, e ->
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e)
                            trySend(UiState.Failure("failed downloading data")).isSuccess
                        }

                        //Log.d(TAG, "Current data: ${snapshot.data}")
                        val data = snapshot?.toObject(CoursesDocument::class.java)
                        //result.invoke(UiState.Success(data?.courses!!))
                        if (data != null) {
                            trySend(UiState.Success(data.courses!!)).isSuccess
                        }
                    }
            awaitClose {
                Log.d(TAG, "Cancelling posts listener")
                listenerRegistration.remove()
            }
        }
    }

    override fun apply(studentInfo: StudentInfo, result: (UiState<String>) -> Unit) {
        database.collection("courses").document("applications")
            .set(studentInfo, SetOptions.merge())
            .addOnSuccessListener {
                result.invoke(UiState.Success("Ваша заявка принята"))
            }
            .addOnFailureListener {
                result.invoke(UiState.Failure("Отсутствует связь с сервером, попропуйте позже"))
            }
    }

}

