package com.cycu.im.yutengdeng.weatherapp.base

interface MvpPresenter<in V : MvpView> {

    fun attachView(view: V)

    fun detachView(retainInstance: Boolean)
}