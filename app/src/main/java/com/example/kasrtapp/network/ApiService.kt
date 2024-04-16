package com.example.kasrtapp.network

import com.example.kasrtapp.model.ResponseUser

import retrofit2.Call

import retrofit2.http.GET

interface ApiService {
    @GET(".")
    fun getUsers(): Call<ResponseUser>
    @GET(".")
    fun getPemanfaatan(): Call<ResponseUser>

}
