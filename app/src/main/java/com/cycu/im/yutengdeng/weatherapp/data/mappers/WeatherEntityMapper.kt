package com.cycu.im.yutengdeng.weatherapp.data.mappers

import com.cycu.im.yutengdeng.weatherapp.data.entities.CityWeatherEntity
import com.cycu.im.yutengdeng.weatherapp.domain.models.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer

fun Observable<Result<CityWeatherEntity>>.mapToCityWeather(): Observable<Result<CityWeather>>
        = this.compose(mapToCityWeatherTransformer())

fun Single<Result<CityWeatherEntity>>.mapToCityWeather(): Single<Result<CityWeather>>
        = this.compose(mapToCityWeatherSingleTransformer())

private fun mapToCityWeatherTransformer() = ObservableTransformer <Result<CityWeatherEntity>, Result<CityWeather>> {
    it.map { entityResult ->
        toCityWeather(entityResult)
    }
}

private fun mapToCityWeatherSingleTransformer() = SingleTransformer <Result<CityWeatherEntity>, Result<CityWeather>> {
    it.map { entityResult ->
        toCityWeather(entityResult)
    }
}

private fun toCityWeather(entityResult: Result<CityWeatherEntity>): Result<CityWeather> {
    return when (entityResult) {
        is Success -> {
            val data = entityResult.data!!
            val weatherModelList = data.weathers.map { w -> w.toWeatherModel() }
            val mainModel = data.main.toMainModel()
            val city = City(data.id, data.name)
            val cw = CityWeather(weatherModelList, mainModel, city)
            Success(cw)
        }
        is InFlight -> InFlight()
        is Failure -> Failure(entityResult.error!!)
    }
}