package com.oxygenbd.exthon.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    
    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String?): List<String> {
        return if (value == null) {
            emptyList()
        } else {
            val listType = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(value, listType)
        }
    }

    @TypeConverter
    fun fromMapToJson(map: Map<String, Any>?): String {
        return Gson().toJson(map)
    }

    @TypeConverter
    fun toMapFromJson(value: String?): Map<String, Any> {
        return if (value == null) {
            emptyMap()
        } else {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            Gson().fromJson(value, type)
        }
    }
}