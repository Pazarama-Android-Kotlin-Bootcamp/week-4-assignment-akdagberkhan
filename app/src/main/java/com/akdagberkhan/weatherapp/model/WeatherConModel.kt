package com.akdagberkhan.weatherapp.model


import com.google.gson.annotations.SerializedName

data class WeatherConModel(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("hourly")
    val hourly: List<Hourly>?,
    @SerializedName("daily")
    val daily: List<Daily>?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("minutely")
    val minutely: List<Minutely>?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int?
)