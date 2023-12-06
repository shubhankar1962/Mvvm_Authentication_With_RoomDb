package com.example.mvvmauthentication.room

import android.app.Application
import android.database.Observable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmauthentication.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserLoginViewmodel(private val repository: LoginRepository): ViewModel() {

//    fun getusers(user: User):LiveData<List<User>>
//    {
//        return repository.getUsers()
//    }

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean> get() = _signUpSuccess

    private val _deleteSuccess = MutableLiveData<Boolean>()
    val deleteSuccess: LiveData<Boolean> get() = _deleteSuccess

    private val _validateSuccess = MutableLiveData<Boolean>()
    val validateSuccess: LiveData<Boolean> get() = _validateSuccess

    fun signUp(user: User)
    {
        viewModelScope.launch {
            repository.insertUser(user)
            Log.e("Tag:viewmodel",user.toString())
            _signUpSuccess.value = true
        }
    }

    fun deleteUserAcc(email:String)
    {
        viewModelScope.launch {
            repository.deleteUser(email)
            _deleteSuccess.value = true
        }
    }

    fun validateUser(mail:String,password:String):User
    {
          return repository.validateUser(mail,password)
    }
}