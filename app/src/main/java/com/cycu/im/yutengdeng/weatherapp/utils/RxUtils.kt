package com.cycu.im.yutengdeng.weatherapp.utils

import io.reactivex.ObservableTransformer

fun <UpStream, DownStream> listTransformer(transform: (UpStream) -> DownStream)
        : ObservableTransformer<List<UpStream>, List<DownStream>> {
    return ObservableTransformer {
        it.map {
            list ->
            list.map { item -> transform(item) }
        }
    }
}