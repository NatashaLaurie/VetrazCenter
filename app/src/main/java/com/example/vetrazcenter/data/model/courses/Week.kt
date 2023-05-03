package com.example.vetrazcenter.data.model.courses

import com.google.firebase.firestore.PropertyName

data class Week(
    @get:PropertyName("monday_lessons")
    @set:PropertyName("monday_lessons")
    var mondayLessons: List<String>? = null,

    @get:PropertyName("tuesday_lessons")
    @set:PropertyName("tuesday_lessons")
    var tuesdayLessons: List<String>? = null,

    @get:PropertyName("wednesday_lessons")
    @set:PropertyName("wednesday_lessons")
    var wednesdayLessons: List<String>? = null,

    @get:PropertyName("thursday_lessons")
    @set:PropertyName("thursday_lessons")
    var thursdayLessons: List<String>? = null,

    @get:PropertyName("friday_lessons")
    @set:PropertyName("friday_lessons")
    var fridayLessons: List<String>? = null,

    @get:PropertyName("saturday_lessons")
    @set:PropertyName("saturday_lessons")
    var saturdayLessons: List<String>? = null,

    @get:PropertyName("sunday_lessons")
    @set:PropertyName("sunday_lessons")
    var sundayLessons: List<String>? = null,
)
