package com.example.mvvmauthentication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmauthentication.R
import com.example.mvvmauthentication.databinding.ActivityLoginBinding
import com.example.mvvmauthentication.repository.LoginRepository
import com.example.mvvmauthentication.room.UserDatabase
import com.example.mvvmauthentication.room.UserLoginViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    lateinit var loginViewmodel: UserLoginViewmodel
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerTv.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        loginViewmodel = UserLoginViewmodel(LoginRepository(UserDatabase.getInstance(this)))


        binding.loginBtn.setOnClickListener{
            val mail = binding.loginmail.text.toString()
            val password =  binding.loginpassword.text.toString()

            val db = UserDatabase.getInstance(this)
            if(mail.isNotBlank() && password.isNotBlank())
            {
               GlobalScope.launch{
                    val user = loginViewmodel.validateUser(mail, password)

                   //val user = db.userDao.validateUser(mail,password)
                   //Log.e("Data",user.toString())
                   withContext(Dispatchers.Main)
                   {
                       if(user != null)
                       {
                           Log.e("TAG:userData",mail+password+user.mail+user.password)
                           if(user.mail == mail && user.password == password)
                           {
                               Toast.makeText(this@LoginActivity, "succesfully login", Toast.LENGTH_SHORT).show()
                           }else{
                               Toast.makeText(this@LoginActivity,"not registered user",Toast.LENGTH_SHORT).show()
                           }
                       }
                       else{
                       Toast.makeText(this@LoginActivity,"user is not present in db",Toast.LENGTH_SHORT).show()
                    }
                   }
//

                }

            }
        }

    }
}