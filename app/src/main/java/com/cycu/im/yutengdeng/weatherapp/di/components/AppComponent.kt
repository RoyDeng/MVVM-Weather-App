package com.cycu.im.yutengdeng.weatherapp.di.components

import android.app.Application
import android.content.Context
import com.cycu.im.yutengdeng.weatherapp.MainApplication
import com.cycu.im.yutengdeng.weatherapp.di.modules.ActivityModule
import com.cycu.im.yutengdeng.weatherapp.di.modules.AppModule
import com.cycu.im.yutengdeng.weatherapp.domain.repositories.WeatherRepository
import com.cycu.im.yutengdeng.weatherapp.executors.ExecutionThread
import com.cycu.im.yutengdeng.weatherapp.executors.PostExecutionThread
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: MainApplication)

    val applicationContext: Context

    val githubRepository: WeatherRepository

    val mainExecutionThread: PostExecutionThread

    val ioExecutionThread: ExecutionThread
}