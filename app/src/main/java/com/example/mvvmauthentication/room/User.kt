package com.example.mvvmauthentication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "loginuser")
data class User (

    @PrimaryKey(autoGenerate = true)
    val userId:Int = 0,

    @ColumnInfo("first_name")
    val firstName:String,

    @ColumnInfo("mail")
    val mail:String,

    @ColumnInfo("password")
    val password:String
)