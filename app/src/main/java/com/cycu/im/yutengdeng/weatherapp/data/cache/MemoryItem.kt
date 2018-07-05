package com.cycu.im.yutengdeng.weatherapp.data.cache

class MemoryItem<out T>(val item: T, private val validationPeriod: Long) {
    private val timeStamp = System.currentTimeMillis()

    val isValid: Boolean
        get() = validationPeriod == MemoryCache.VALIDATION_INFINITY
                || timeStamp + validationPeriod >= System.currentTimeMillis()

}