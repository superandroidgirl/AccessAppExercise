package com.example.kotlinsample.model

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val time: String,
    val cityInfo: CityInfo,
    val date: String,
    val message: String,
    val status: Int,
    val data: Data
)

@Serializable
data class Data(
    val shidu: String,
    val pm25: Double,
    val pm10: Double,
    val quality: String,
    val wendu: String,
    val ganmao: String,
    val yesterday: Yesterday,
    val forecast: List<Forecast>

) {
    override fun toString(): String {
        return "Data(shidu='$shidu', pm25=$pm25, pm10=$pm10, quality='$quality', wendu='$wendu', ganmao='$ganmao', yesterday=$yesterday, forecast=$forecast)"
    }
}

@Serializable
data class Yesterday(
    val date: String,
    val sunrise: String,
    val high: String,
    val low: String,
    val sunset: String,
    val aqi: Double,
    val fx: String,
    val fl: String,
    val type: String,
    val notice: String
)

@Serializable
data class Forecast(
    val date: String,
    val sunrise: String,
    val high: String,
    val low: String,
    val sunset: String,
    val aqi: Double,
    val fx: String,
    val fl: String,
    val type: String,
    val notice: String
)

@Serializable
data class CityInfo(
    val city: String,
    val cityId: String,
    val parent: String,
    val updateTime: String
)