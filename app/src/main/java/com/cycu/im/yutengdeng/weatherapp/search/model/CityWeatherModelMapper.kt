package com.cycu.im.yutengdeng.weatherapp.search.model

import com.cycu.im.yutengdeng.weatherapp.domain.models.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

fun toCityWeatherModel() = ObservableTransformer<Result<CityWeather>, Result<CityWeatherModel>> {
    it.map { result ->
        when (result) {
            is Success -> {
                val data = result.data!!
                val model = CityWeatherModel(data.weathers.first(), data.main.temp, data.city)
                Success(model)
            }
            is InFlight -> InFlight()
            is Failure -> Failure<CityWeatherModel>(result.error!!)
        }
    }
}

fun Observable<Result<CityWeather>>.mapToCityWeatherModel() = this.compose(toCityWeatherModel())