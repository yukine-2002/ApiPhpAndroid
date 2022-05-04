package com.example.testrretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.figure.retrofit.RetroServiceInterface
import com.app.figure.retrofit.retroInstance
import com.example.testrretrofit.model.InfoUser
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        getAllPerson()

    }

    override fun onResume() {
        super.onResume()
        var sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        var user = sharedPreferences.getString("info","")
        Log.e("check - data ",user.toString())
        var hellCus : TextView = findViewById(R.id.helloCus)

         var gson  = Gson()
         var info : InfoUser = gson.fromJson(user,InfoUser::class.java)

        if(info.phanquyen == 0){
            hellCus.text = "hello admin " + info.fullname
        }else{
            hellCus.text = "hello cus " + info.fullname
        }
    }

    fun getAllPerson () {
        var retroInstance = retroInstance.getRetroInstance()
        var retroService = retroInstance.create(RetroServiceInterface::class.java)
        var recyclerView : RecyclerView = findViewById(R.id.listUser)
        var call = retroService.getUser()
        var dataAdapter = AdapterUser(this)
        call.enqueue(object : Callback<ArrayList<InfoUser>>{
            override fun onResponse(call: Call<ArrayList<InfoUser>>, response: Response<ArrayList<InfoUser>>) {

                response.body()?.let { dataAdapter.getList(it) }
                dataAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<ArrayList<InfoUser>>, t: Throwable) {
                Log.e("check",t.toString())
            }

        })

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = dataAdapter

    }
}