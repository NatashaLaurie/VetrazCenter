package com.example.vetrazcenter.domain.use_case

import com.example.vetrazcenter.data.model.student.StudentInfo
import com.example.vetrazcenter.domain.repository.ApplicationRepository

class AddApplication(
    private val repo: ApplicationRepository
) {
    suspend operator fun invoke(
        studentInfo: StudentInfo
    ) = repo.apply(studentInfo)
}