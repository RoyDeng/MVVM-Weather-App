package com.cycu.im.yutengdeng.weatherapp.search

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.cycu.im.yutengdeng.weatherapp.R
import com.cycu.im.yutengdeng.weatherapp.custom.BindingRecyclerViewAdapter
import com.cycu.im.yutengdeng.weatherapp.search.model.CityWeatherModel
import com.cycu.im.yutengdeng.weatherapp.utils.artResource
import com.cycu.im.yutengdeng.weatherapp.utils.celsiusDegrees

class CityWeatherAdapter(private val context: Context) : BindingRecyclerViewAdapter() {

    private val cityWeatherModels: MutableList<CityWeather> = arrayListOf()

    class CityWeather(val cityName: String, val temp: String, val icon: Drawable)

    override fun getObjForPosition(position: Int) = cityWeatherModels[position]

    override fun getLayoutIdForPosition(position: Int) = R.layout.item_city_weather

    override fun getItemCount() = cityWeatherModels.size

    fun addCityWeather(cityWeatherModel: CityWeatherModel) {

        val drawable = ContextCompat.getDrawable(context, cityWeatherModel.artResource())
        val cityWeather = CityWeather(cityWeatherModel.city.name, cityWeatherModel.celsiusDegrees(context), drawable)
        cityWeatherModels.add(0, cityWeather)
        notifyItemInserted(0)
    }
}