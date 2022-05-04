package com.example.testrretrofit.model

data class Login(
    val infoUser: InfoUser,
    val message: String,
    val success: Int
)

data class InfoUser(
    val diachi: String,
    val fullname: String,
    val id: String,
    val password: String,
    val phanquyen: Int,
    val sdt: String,
    val username: String
)