package com.example.vetrazcenter.domain.use_case

import com.example.vetrazcenter.domain.repository.CoursesRepository

class GetCoursesByCategory(
    private val repo: CoursesRepository
) {
    operator fun invoke(category: String) = repo.getCoursesListByCategory(category)
}