package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ProductList.ProductListActivity
import com.example.myapplication.R
import com.example.myapplication.localstorage.sharedpref.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextTextPassword)
        button = findViewById(R.id.button)
        loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)

                Log.d("sss_bug", "Ping")
        loginViewModel.navigationEventLiveData.observe(this) {
            if(it!=null){
                Log.d("sss_bug", "it was NOT null")
            val content = it.getContentIfNotHandled()
                if(content==null){
                Log.d("sss_bug", "content was null and has been handled")
                }
                else if (content==Unit){
                Log.d("sss_bug", "content was unit and has not been handeled, will handle now.")
                    startActivity(Intent(this, ProductListActivity::class.java))
                    finish()
                }
            }else{
                Log.d("sss_bug", "it was null ")
            }
        }

        button.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginViewModel.onDataSubmit(email,password)
        }
    }
}