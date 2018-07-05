package com.cycu.im.yutengdeng.weatherapp.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cycu.im.yutengdeng.weatherapp.di.utils.ViewModelKey
import com.cycu.im.yutengdeng.weatherapp.factories.WeatherViewModelFactory
import com.cycu.im.yutengdeng.weatherapp.search.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindUserViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: WeatherViewModelFactory): ViewModelProvider.Factory
}