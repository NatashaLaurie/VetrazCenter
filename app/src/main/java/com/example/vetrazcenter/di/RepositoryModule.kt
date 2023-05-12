package com.example.vetrazcenter.di

import com.example.vetrazcenter.data.repository.ApplicationRepositoryImpl
import com.example.vetrazcenter.data.repository.CoursesRepositoryImpl
import com.example.vetrazcenter.domain.repository.ApplicationRepository
import com.example.vetrazcenter.domain.repository.CoursesRepository
import com.example.vetrazcenter.domain.use_case.*
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

    @Singleton
    @Provides
    fun providesApplicationRepository(database: FirebaseFirestore): ApplicationRepository {
        return ApplicationRepositoryImpl(database)
    }


    @Provides
    fun provideCoursesUseCases(
        coursesRepo: CoursesRepository,
    ) = CoursesUseCases(
        getOngoingCourses = GetOngoingCourses(coursesRepo),
        getCoursesByCategory = GetCoursesByCategory(coursesRepo)
    )

    @Provides
    fun provideApplicationUseCases(
        applicationRepo: ApplicationRepository
    ) = CourseApplicationUseCase(
        addApplication = AddApplication(applicationRepo)
    )
}