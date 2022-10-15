package com.akdagberkhan.weatherapp.api

import com.akdagberkhan.weatherapp.model.WeatherConModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("onecall")
    fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String = "weekly"
    ): Call<WeatherConModel >

    @GET("onecall?lat=33.44&lon=-94.04&appid=8ddadecc7ae4f56fee73b2b405a63659&exclude=daily")
    fun getDeneme() : Call<WeatherConModel>
}