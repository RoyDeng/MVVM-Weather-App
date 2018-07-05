package com.cycu.im.yutengdeng.weatherapp.search.model

import com.cycu.im.yutengdeng.weatherapp.domain.models.City
import com.cycu.im.yutengdeng.weatherapp.domain.models.Weather

data class CityWeatherModel(val weather: Weather, val temp: Double, val city: City)