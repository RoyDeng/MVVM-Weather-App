package com.cycu.im.yutengdeng.weatherapp.data.stores

import com.cycu.im.yutengdeng.weatherapp.BuildConfig
import com.cycu.im.yutengdeng.weatherapp.data.api.RestApi
import com.cycu.im.yutengdeng.weatherapp.data.cache.MemoryCache
import com.cycu.im.yutengdeng.weatherapp.data.entities.CityWeatherEntity
import com.cycu.im.yutengdeng.weatherapp.data.mappers.mapToCityWeather
import com.cycu.im.yutengdeng.weatherapp.domain.models.*
import com.cycu.im.yutengdeng.weatherapp.domain.repositories.WeatherRepository
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityWeatherDataStore @Inject constructor(val restApi: RestApi) : WeatherRepository {

    // TODO: inject this
    private val memoryCache = MemoryCache<CityWeatherEntity>()
    private val apiKey = BuildConfig.OPENWEATHERMAP_API_KEY

    override fun loadCityWeatherData(cityNames: Observable<String>): Observable<Result<CityWeather>> {
        return cityNames
                .switchMap {
                    load(it)
                }
                .mapToCityWeather()
    }

    override fun fetchCityWeatherData(cityName: String): Single<Result<CityWeather>> {
        return Maybe
                .concat(loadFromCache(cityName), loadFromDb(cityName), loadFromNetwork(cityName))
                .firstOrError()
                .mapToCityWeather()
                .onErrorReturn { Failure(it) }
    }

    /**
     * Hides the data source from where the data origins. Catches possible errors and maps them to
     * @see InFlight so the downstream observes don't terminate ( onError is a terminal event)
     */
    private fun load(cityName: String): Observable<Result<CityWeatherEntity>>{
        return Maybe.concat(loadFromCache(cityName), loadFromDb(cityName), loadFromNetwork(cityName))
                .toObservable()
                .startWith(InFlight())
                .onErrorReturn { Failure(it) }
    }

    private fun loadFromNetwork(searchTerm: String): Maybe<Result<CityWeatherEntity>> {
        return restApi.getWeatherByCityName(searchTerm, apiKey)
                .flatMap {
                    if (!it.isSuccessful) {
                        Single.just(Failure<CityWeatherEntity>(Throwable(it.errorBody().string())))
                    } else {
                        Single.just(Success(it.body()))
                    }
                }
                .toMaybe()
    }

    private fun loadFromCache(searchTerm: String): Maybe<Result<CityWeatherEntity>> {
        return memoryCache[searchTerm].map { Success(it) }
    }

    private fun loadFromDb(searchTerm: String) = Maybe.empty<Result<CityWeatherEntity>>()

    private fun saveToCache(key: String, entity: CityWeatherEntity) {
        memoryCache.put(key, entity, MemoryCache.VALIDATION_PERIOD)
    }
}