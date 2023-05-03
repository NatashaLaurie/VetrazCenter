package com.example.vetrazcenter.domain.use_case

import com.example.vetrazcenter.domain.repository.CoursesRepository

class GetOngoingCourses(
    private val repo: CoursesRepository
) {
    operator fun invoke() = repo.getOngoingCoursesList()
}