package com.cycu.im.yutengdeng.weatherapp.executors

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IoExecutionThread @Inject constructor() : ExecutionThread {
    override val scheduler: Scheduler = Schedulers.io()
}