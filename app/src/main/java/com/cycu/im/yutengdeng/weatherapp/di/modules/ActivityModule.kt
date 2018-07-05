package com.cycu.im.yutengdeng.weatherapp.di.modules

import com.cycu.im.yutengdeng.weatherapp.search.view.MvvmSearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMvvmSearchActivity(): MvvmSearchActivity
}