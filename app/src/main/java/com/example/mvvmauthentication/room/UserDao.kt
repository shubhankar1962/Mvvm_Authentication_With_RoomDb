package com.example.mvvmauthentication.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Query("select * from loginuser order by first_name Asc")
    fun getAllUser(): LiveData<List<User>>

    @Query("select * from loginuser where first_name like:firstName")
    fun getUserByName(firstName:String):User?

    @Query("delete from loginuser where mail =:email")
    fun deleteUser(email:String)

    @Query("select * from loginuser where mail =:email And password =:upassword")
    fun validateUser(email: String, upassword:String):User
}