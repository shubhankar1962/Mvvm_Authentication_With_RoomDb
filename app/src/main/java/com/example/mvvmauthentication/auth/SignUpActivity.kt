package com.example.mvvmauthentication.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mvvmauthentication.R
import com.example.mvvmauthentication.databinding.ActivityLoginBinding
import com.example.mvvmauthentication.databinding.ActivitySignUpBinding
import com.example.mvvmauthentication.repository.LoginRepository
import com.example.mvvmauthentication.room.User
import com.example.mvvmauthentication.room.UserDao
import com.example.mvvmauthentication.room.UserDatabase
import com.example.mvvmauthentication.room.UserLoginViewmodel

class SignUpActivity : AppCompatActivity() {

    var firstName=""
    var password = ""
    var mail = ""
    var cpassword = ""
   lateinit var signUpViewmodel:UserLoginViewmodel
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)




        signUpViewmodel = UserLoginViewmodel(LoginRepository(UserDatabase.getInstance(this)))




        binding.signUpBtn.setOnClickListener{

            firstName = binding.etName.text.toString()
             mail = binding.signUpmail.text.toString()
             password = binding.signUppassword.text.toString()
             cpassword = binding.cnfmPassword.text.toString()
             if(!firstName.isEmpty() && !password.isEmpty() && !mail.isEmpty() && !cpassword.isEmpty())
            {

            val user = User(0,firstName,mail,password)
                if(password == cpassword)
                {
                    signUpViewmodel.signUp(user)
                }
                else{
                    Toast.makeText(this,"password not matches",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"field shouldn't be empty",Toast.LENGTH_SHORT).show()
            }

        }

        signUpViewmodel.signUpSuccess.observe(this, Observer { success ->
            if (success) {
                // Handle successful signup, navigate to the next screen or show a success message
                Toast.makeText(this, "successful register user",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "failed to  register user",Toast.LENGTH_SHORT).show()

            }
        })

        binding.deleteuserBtn.setOnClickListener{
             mail = binding.signUpmail.text.toString()
            signUpViewmodel.deleteUserAcc(mail)
        }

        signUpViewmodel.deleteSuccess.observe(this, Observer{success ->
            if(success)
            {
                Toast.makeText(this, "successful deleted user",Toast.LENGTH_SHORT).show()
            }
        })


    }
}