package com.example.vetrazcenter.data.local

import androidx.room.TypeConverter
import com.example.vetrazcenter.data.model.local.courses.LocationInfo
import com.example.vetrazcenter.data.model.local.courses.Schedule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    private val gson: Gson = Gson()

    @TypeConverter
    fun toScheduleJson(schedule: List<Schedule>?): String {
        return gson.toJson(
            schedule,
            object : TypeToken<List<Schedule>>() {}.type
        ) ?: ""
    }

    @TypeConverter
    fun fromScheduleJson(json: String): List<Schedule> {
        return gson.fromJson<List<Schedule>>(
            json,
            object : TypeToken<List<Schedule>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toLocationInfoJson(locationInfo: LocationInfo?): String {
        return gson.toJson(locationInfo)
    }

    @TypeConverter
    fun fromLocationInfoJson(json: String): LocationInfo? {
        return gson.fromJson(json, LocationInfo::class.java)
    }

}