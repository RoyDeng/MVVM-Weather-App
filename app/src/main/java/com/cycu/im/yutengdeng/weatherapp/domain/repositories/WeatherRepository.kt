package com.cycu.im.yutengdeng.weatherapp.domain.repositories

import com.cycu.im.yutengdeng.weatherapp.domain.models.CityWeather
import com.cycu.im.yutengdeng.weatherapp.domain.models.Result
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherRepository {

    // Fetches single weather data about a city
    fun fetchCityWeatherData(cityName: String): Single<Result<CityWeather>>

    // Continuous loads weather data about cities reacting on cityNames observable
    fun loadCityWeatherData(cityNames: Observable<String>): Observable<Result<CityWeather>>
}