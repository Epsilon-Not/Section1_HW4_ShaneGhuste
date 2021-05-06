package com.example.section1_hw4_shaneghuste

import com.example.section1_hw4_shaneghuste.api.CatJson
import retrofit2.Call
import retrofit2.http.GET

interface APIRequest {
    @GET("/facts/random")
    fun getCatFacts(): Call<CatJson>
}