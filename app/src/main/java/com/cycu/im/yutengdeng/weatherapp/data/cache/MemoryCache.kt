package com.cycu.im.yutengdeng.weatherapp.data.cache

import io.reactivex.Maybe

class MemoryCache<T> {

    private val memoryCache = HashMap<String, MemoryItem<T>>()

    fun put(key: String, value: T, validationPeriod: Long) {

        memoryCache.put(key, MemoryItem(value, validationPeriod))
    }

    operator fun get(key: String): Maybe<T> {

        val memoryItem = memoryCache[key]

        return if (memoryItem != null && memoryItem.isValid) Maybe.just(memoryItem.item) else Maybe.empty<T>()
    }

    fun getAsValue(key: String) = memoryCache[key]?.item

    fun clear() {
        memoryCache.clear()
    }

    companion object {
        // 5 min
        val VALIDATION_PERIOD = (1000 * 60 * 5).toLong()

        //Special value for infinity
        val VALIDATION_INFINITY: Long = -1
    }
}