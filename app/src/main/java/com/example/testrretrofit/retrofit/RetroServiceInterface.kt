package com.app.figure.retrofit

import com.example.testrretrofit.model.InfoUser
import com.example.testrretrofit.model.Login
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetroServiceInterface {

    @GET("update.php")
    fun update(@Query("id") id : String,@Query("fullname") fullname : String,@Query("username") username : String,@Query("password") password : String,@Query("diachi") diachi : String,@Query("sdt") sdt : String) : Call<ResponseBody>


    @GET("insetUSer.php")
    fun insetUSer(@Query("fullname") fullname : String,@Query("username") username : String,@Query("password") password : String,@Query("diachi") diachi : String,@Query("sdt") sdt : String) : Call<ResponseBody>

    @GET("getUser.php")
    fun getUser() : Call<ArrayList<InfoUser>>

    @GET("delete.php")
    fun delete(@Query("id") id : String) : Call<ResponseBody>

    @GET("login.php")
    fun login(@Query("username") username : String, @Query("password") password : String) : Call<Login>

}