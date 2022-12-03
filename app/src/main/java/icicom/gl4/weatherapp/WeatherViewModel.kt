package icicom.gl4.weatherapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherViewModel : ViewModel() {
    val weather : MutableLiveData<WeatherData> by lazy {
        MutableLiveData<WeatherData>()
    }
    init {
    getWeather("London")
    }
    fun getWeather(Country : String)
    {
        RetrofitHelper.retrofitService.getWeather(Country).enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (response.isSuccessful)
                    weather.value = response.body()
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.d("Tag", t.message.toString())
            }


        })}
}
