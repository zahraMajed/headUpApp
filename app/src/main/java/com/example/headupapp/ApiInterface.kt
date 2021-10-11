package com.example.headupapp

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    //get request: get -read- data from API
    @Headers("Content-Type: application/json")
    @GET("/celebrities/")
    fun getDate(): Call<List<myData.celeb>>?

    //post request: add -insert- data to API
    //we must use @Body
    @Headers("Content-Type: application/json")
    @POST("/celebrities/")
    fun addUser (@Body info:myData.celeb): Call<myData.celeb>

    //put request: update of replace full specific object
    //we must use @Path to change individual fields
    @Headers("Content-Type: application/json")
    @PUT("/celebrities/{id}")
    fun updateUser(@Path("id") id:Int, @Body info:myData.celeb): Call<myData.celeb>

    //delete request: delete full specific object
    //we must use @Path to change individual fields
    //this must return Call<Void> to overwrite an existing post
    @Headers("Content-Type: application/json")
    @DELETE("/celebrities/{id}")
    fun deleteUser(@Path("id") id:Int): Call<Void>
}