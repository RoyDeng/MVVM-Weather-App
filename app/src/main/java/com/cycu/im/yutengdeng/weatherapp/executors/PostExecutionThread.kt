package com.cycu.im.yutengdeng.weatherapp.executors

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}