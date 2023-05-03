package com.example.vetrazcenter.data.repository

import android.util.Log
import com.example.vetrazcenter.core.Constants.COURSES
import com.example.vetrazcenter.core.Constants.RECRUITING_IS_OPEN
import com.example.vetrazcenter.core.Constants.TAG
import com.example.vetrazcenter.data.model.courses.Course
import com.example.vetrazcenter.domain.repository.CoursesRepository
import com.example.vetrazcenter.domain.model.Response.Success
import com.example.vetrazcenter.domain.model.Response.Failure
import com.example.vetrazcenter.domain.repository.CoursesResponse
import com.google.firebase.firestore.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepositoryImpl @Inject constructor(
    database: FirebaseFirestore
) : CoursesRepository {

    private val collectionGroup = database.collectionGroup(COURSES)
    override fun getOngoingCoursesList() = callbackFlow {
        val listenerRegistration =
            collectionGroup.whereEqualTo(RECRUITING_IS_OPEN, true)
                .addSnapshotListener { snapshot, e ->
                    val coursesResponse = if (snapshot != null) {
                        val courses = snapshot.toObjects(Course::class.java)
                        Log.d(TAG, "Current data: $courses")
                        Success(courses)
                    } else {
                        Log.w(TAG, "Listen failed.", e)
                        Failure(e)
                    }
                    trySend(coursesResponse)
                }
        awaitClose {
            listenerRegistration.remove()
        }
    }


    fun Query.snapshotFlow(): Flow<QuerySnapshot> = callbackFlow {
        val listenerRegistration = addSnapshotListener { value, error ->
            if (error != null) {
                close()
                return@addSnapshotListener
            }
            if (value != null)
                trySend(value)
        }
        awaitClose {
            listenerRegistration.remove()
        }
    }

    fun <T> DocumentReference.addSnapshotListenerFlow(dataType: Class<T>): Flow<T?> = callbackFlow {
        val listener = object : EventListener<DocumentSnapshot> {
            override fun onEvent(snapshot: DocumentSnapshot?, exception: FirebaseFirestoreException?) {
                if (exception != null) {
                    // An error occurred
                    cancel()
                    return
                }

                if (snapshot != null && snapshot.exists()) {
                    // The document has data
                    val data = snapshot.toObject(dataType)
                    trySend(data)
                } else {
                    // The document does not exist or has no data
                }
            }
        }

        val registration = addSnapshotListener(listener)
        awaitClose { registration.remove() }
    }

    override fun getCoursesList(): Flow<CoursesResponse> {
        TODO("Not yet implemented")
    }


}

