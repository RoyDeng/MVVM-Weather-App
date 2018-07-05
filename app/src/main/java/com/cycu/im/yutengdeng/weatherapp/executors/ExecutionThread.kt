package com.cycu.im.yutengdeng.weatherapp.executors

import io.reactivex.Scheduler

interface ExecutionThread {
    val scheduler: Scheduler
}