package com.cycu.im.yutengdeng.weatherapp.domain.usecases

import io.reactivex.Observable
import io.reactivex.Single

interface SingleUseCase<in Params, Result> {
    fun execute(params: Params): Single<Result>
}

interface ObservableUseCase<in Params, Result> {
    fun execute(params: Params): Observable<Result>
}