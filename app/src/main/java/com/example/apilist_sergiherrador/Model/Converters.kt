package com.example.apilist_sergiherrador.Model

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListToString(list:List<String>):String{
        return list.joinToString { "," }
    }

    @TypeConverter
    fun fromStringToList(string:String):List<String>{
        return string.split(",")
    }
}