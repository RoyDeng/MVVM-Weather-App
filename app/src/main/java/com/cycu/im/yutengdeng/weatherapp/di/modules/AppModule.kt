package com.cycu.im.yutengdeng.weatherapp.di.modules

import android.app.Application
import android.content.Context
import com.cycu.im.yutengdeng.weatherapp.BuildConfig
import com.cycu.im.yutengdeng.weatherapp.data.api.RestApi
import com.cycu.im.yutengdeng.weatherapp.data.stores.CityWeatherDataStore
import com.cycu.im.yutengdeng.weatherapp.domain.repositories.WeatherRepository
import com.cycu.im.yutengdeng.weatherapp.executors.ExecutionThread
import com.cycu.im.yutengdeng.weatherapp.executors.IoExecutionThread
import com.cycu.im.yutengdeng.weatherapp.executors.MainExecutionThread
import com.cycu.im.yutengdeng.weatherapp.executors.PostExecutionThread
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {

    private val baseUrl = BuildConfig.OPENWEATHERMAP_URL

    @Provides
    @Singleton
    fun providesPostExecutionThread(mainExecutionThread: MainExecutionThread): PostExecutionThread = mainExecutionThread

    @Provides
    @Singleton
    fun providesExecutionThread(ioExecutionThread: IoExecutionThread): ExecutionThread = ioExecutionThread

    @Provides
    @Singleton
    fun providesApplicationContext(application: Application): Context {

        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        // add custom deserializers here
        return GsonBuilder()
                .create()
    }

    @Provides
    @Singleton
    fun providesRestAdapter(okHttpClient: OkHttpClient, gson: Gson): Retrofit {

        return Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    fun providesResApi(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGithubRepository(restApi: RestApi): WeatherRepository {
        return CityWeatherDataStore(restApi)
    }
}