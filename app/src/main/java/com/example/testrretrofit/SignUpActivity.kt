package com.example.testrretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.app.figure.retrofit.RetroServiceInterface
import com.app.figure.retrofit.retroInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var username : EditText = findViewById(R.id.usenamedk)
        var password : EditText = findViewById(R.id.passworddk)
        var fullname : EditText = findViewById(R.id.fullname)
        var diachi : EditText = findViewById(R.id.address)
        var sdt : EditText = findViewById(R.id.sdt)

        var signUp : Button = findViewById(R.id.signUp)
        signUp.setOnClickListener {
            var retroInstance = retroInstance.getRetroInstance()
            var retroServiec = retroInstance.create(RetroServiceInterface::class.java)
            var call = retroServiec.insetUSer(fullname.text.toString(),username.text.toString(),password.text.toString(),diachi.text.toString(),sdt.text.toString())
            call.enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    var intent = Intent(this@SignUpActivity,LoginActivity::class.java)
                    startActivity(intent)
                    Log.e("check ",response.body().toString())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("fail",t.toString())
                }

            })

        }

        var back : Button = findViewById(R.id.back)

        back.setOnClickListener {
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}