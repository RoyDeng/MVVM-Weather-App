package com.cycu.im.yutengdeng.weatherapp.domain.usecases

import com.cycu.im.yutengdeng.weatherapp.domain.models.CityWeather
import com.cycu.im.yutengdeng.weatherapp.domain.models.Result
import com.cycu.im.yutengdeng.weatherapp.domain.repositories.WeatherRepository
import com.cycu.im.yutengdeng.weatherapp.domain.usecases.GetCityWeatherUseCase.Params
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(private val repository: WeatherRepository)
    : ObservableUseCase<Params, Result<CityWeather>> {

    class Params(val cityNames: Observable<CharSequence>)

    override fun execute(params: Params): Observable<Result<CityWeather>> {

        // apply business logic
        val cityNamesObservable = params
                .cityNames
                .map{it.toString()}
                .debounce(400L, TimeUnit.MILLISECONDS)
                .map { it.trim() }
                .filter { it.length > 2 }

        return repository.
                loadCityWeatherData(cityNames = cityNamesObservable)
    }
}