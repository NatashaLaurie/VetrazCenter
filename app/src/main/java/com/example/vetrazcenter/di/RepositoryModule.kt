package com.example.vetrazcenter.di

import com.example.vetrazcenter.data.repository.CoursesRepositoryImpl
import com.example.vetrazcenter.domain.repository.CoursesRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun providesCoursesRepository(database: FirebaseFirestore): CoursesRepository {
        return CoursesRepositoryImpl(database)
    }
}