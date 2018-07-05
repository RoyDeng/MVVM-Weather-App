package com.cycu.im.yutengdeng.weatherapp.data.api

import com.cycu.im.yutengdeng.weatherapp.data.entities.CityWeatherEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("weather")
    fun getWeatherByCityName(@Query("q") cityName: String, @Query("appid") appId: String): Single<Response<CityWeatherEntity>>
}