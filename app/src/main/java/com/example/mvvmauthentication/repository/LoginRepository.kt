package com.example.mvvmauthentication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mvvmauthentication.room.User
import com.example.mvvmauthentication.room.UserDao
import com.example.mvvmauthentication.room.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(val userDb:UserDatabase) {

//    fun getUsers():LiveData<List<User>>
//    {
//        return userDb.userDao.getAllUser()
//    }

    suspend fun insertUser(user: User)
    {
        withContext(Dispatchers.IO)
        {
            userDb.userDao.insertUser(user)
            Log.e("TAG:userinserted", user.toString())

        }
    }

   suspend fun deleteUser(email:String)
    {
        withContext(Dispatchers.IO)
        {
            userDb.userDao.deleteUser(email)

        }

    }

    fun validateUserName(userName: String):User?
    {
        return userDb.userDao.getUserByName(userName)
    }
    val users :LiveData<List<User>> = userDb.userDao.getAllUser()

     fun validateUser(email: String,password:String):User
    {
        val data = userDb.userDao.validateUser(email, password)
        return userDb.userDao.validateUser(email,password)
    }
}