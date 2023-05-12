package com.example.vetrazcenter.data.repository

import android.util.Log
import com.example.vetrazcenter.data.model.remote.courses.CourseDto
import com.example.vetrazcenter.domain.model.Response.Failure
import com.example.vetrazcenter.domain.model.Response.Success
import com.example.vetrazcenter.domain.repository.CoursesRepository
import com.example.vetrazcenter.utils.Constants.CATEGORIES
import com.example.vetrazcenter.utils.Constants.COURSES
import com.example.vetrazcenter.utils.Constants.NAME
import com.example.vetrazcenter.utils.Constants.RECRUITING_IS_OPEN
import com.example.vetrazcenter.utils.Constants.TAG
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CoursesRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : CoursesRepository {

    private val collectionGroup = firebaseFirestore.collectionGroup(COURSES)
    override fun getCoursesListByCategory(category: String) = callbackFlow {
        val snapshotListener = firebaseFirestore.collection(CATEGORIES)
            .document(category).collection(COURSES).orderBy(NAME)
            .addSnapshotListener { snapshot, e ->
                val coursesResponse = if (snapshot != null) {
                    val course = snapshot.toObjects(CourseDto::class.java)
                    Log.d(TAG, "Current data: $course")
                    Success(course.map { it.toDomainObject() })
                } else {
                    Log.w(TAG, "Listen failed.", e)
                    Failure(e)
                }
                trySend(coursesResponse)
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getOngoingCoursesList() = callbackFlow {
        val snapshotListener =
            collectionGroup.whereEqualTo(RECRUITING_IS_OPEN, true).limit(5)
                .addSnapshotListener { snapshot, e ->
                    val coursesResponse = if (snapshot != null) {
                        val course = snapshot.toObjects(CourseDto::class.java)
                        Log.d(TAG, "Current data: $course")
                        Success(course.map { it.toDomainObject() })
                    } else {
                        Log.w(TAG, "Listen failed.", e)
                        Failure(e)
                    }
                    trySend(coursesResponse)
                }
        awaitClose {
            snapshotListener.remove()
        }
    }


}

