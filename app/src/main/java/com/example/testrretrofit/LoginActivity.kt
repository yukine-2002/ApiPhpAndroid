package com.example.testrretrofit

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.app.figure.retrofit.RetroServiceInterface
import com.app.figure.retrofit.retroInstance
import com.example.testrretrofit.model.InfoUser
import com.example.testrretrofit.model.Login
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var username : EditText = findViewById(R.id.usename)
        var password : EditText = findViewById(R.id.password)

        var login : Button = findViewById(R.id.login)
        login.setOnClickListener {
            login(username.text.toString(),password.text.toString())
        }


        var back : Button = findViewById(R.id.back)

        back.setOnClickListener {
            var intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun login(username : String,password: String){

        var retroInstance = retroInstance.getRetroInstance()
        var retroService = retroInstance.create(RetroServiceInterface::class.java)

        var call = retroService.login(username,password)

        call.enqueue(object : Callback<Login>{
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if(response.body()?.success == 1){

                    var sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
                    var gson = Gson()
                    var putUser  = gson.toJson(response.body()?.infoUser)
                    sharedPreferences.edit().putString("info",putUser).commit()

                    var intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                }

                Log.w("check",response.body().toString())
            }

            override fun onFailure(call: Call<Login>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "failt", Toast.LENGTH_SHORT).show()
            }

        })

    }
}