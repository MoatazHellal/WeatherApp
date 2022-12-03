package icicom.gl4.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
   private val viewModel : WeatherViewModel by viewModels()
    val spinner : Spinner by lazy { findViewById(R.id.spinner) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var Countries = listOf<String>("Tunis","London","Paris")
        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Countries)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.getWeather(spinner.selectedItem.toString())
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }

        val Location = findViewById<TextView>(R.id.textView)
         val Temperature = findViewById<TextView>(R.id.textView2)
         val Humidity = findViewById<TextView>(R.id.textView4)
         val Pressure = findViewById<TextView>(R.id.textView5)
         val Weather = findViewById<TextView>(R.id.textView3)
        viewModel.weather.observe(this, Observer {
            if(it!=null){
                Pressure.text = it.main.pressure.toString()
                Location.text = it.sys.country
                Temperature.text = (it.main.temp - 273).toString()+"C"
                Humidity.text = it.main.humidity.toString()
                Weather.text = it.weather[0].description}

        })
        }
}