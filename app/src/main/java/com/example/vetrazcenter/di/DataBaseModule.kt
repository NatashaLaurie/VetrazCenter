package com.example.vetrazcenter.di

import android.content.Context
import androidx.room.Room
import com.example.vetrazcenter.data.local.CourseDao
import com.example.vetrazcenter.data.local.CourseDataBase
import com.example.vetrazcenter.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): CourseDataBase {
        return Room.databaseBuilder(context, CourseDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideArticleDao(db: CourseDataBase): CourseDao {
        return db.getCourseDao()
    }

}