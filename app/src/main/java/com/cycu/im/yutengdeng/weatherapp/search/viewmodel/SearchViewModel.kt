package com.cycu.im.yutengdeng.weatherapp.search.viewmodel

import android.arch.lifecycle.ViewModel
import com.cycu.im.yutengdeng.weatherapp.domain.models.Result
import com.cycu.im.yutengdeng.weatherapp.domain.usecases.GetCityWeatherUseCase
import com.cycu.im.yutengdeng.weatherapp.domain.usecases.GetCityWeatherUseCase.Params
import com.cycu.im.yutengdeng.weatherapp.executors.PostExecutionThread
import com.cycu.im.yutengdeng.weatherapp.search.model.CityWeatherModel
import com.cycu.im.yutengdeng.weatherapp.search.model.mapToCityWeatherModel
import io.reactivex.Observable
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val useCase: GetCityWeatherUseCase, private val mainThread: PostExecutionThread) : ViewModel() {

    fun search(textChanges: Observable<CharSequence>): Observable<Result<CityWeatherModel>> {
        return useCase
                .execute(Params(textChanges))
                .mapToCityWeatherModel()
                .observeOn(mainThread.scheduler)
    }
}