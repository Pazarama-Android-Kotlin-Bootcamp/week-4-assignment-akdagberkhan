package com.akdagberkhan.weatherapp.view.fragments

import android.os.Bundle
import android.os.RecoverySystem
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akdagberkhan.weatherapp.R
import com.akdagberkhan.weatherapp.adapter.DailyAdapter
import com.akdagberkhan.weatherapp.api.ApiClient
import com.akdagberkhan.weatherapp.model.WeatherConModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {

    private lateinit var tvCountry: TextView
    private lateinit var tvDegree: TextView
    private lateinit var ivNowIcon: ImageView
    private lateinit var rvDaily : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCountry = view.findViewById(R.id.tvCountry)
        tvDegree = view.findViewById(R.id.tvDegree)
        ivNowIcon = view.findViewById(R.id.ivNowIcon)
        rvDaily = view.findViewById(R.id.rvDaily)

        arguments?.let {
            val lonData = it.getString("lon")
            val latData = it.getString("lat")

            //getDeneme()
            getData(lonData.toString(),latData.toString())
        }
    }

    private fun getData(lon : String, lat: String)
    {

        ApiClient.getApiService().getWeatherData(lat.toDouble(),lon.toDouble()).enqueue(object : Callback<WeatherConModel>{
            override fun onResponse(call: Call<WeatherConModel>, response: Response<WeatherConModel>) {
                if (response.isSuccessful) {
                    val everything = response.body()
                    everything?.let {
                        tvCountry.text=it.timezone
                        Picasso.get().load("http://openweathermap.org/img/wn/"+it.current?.weather?.get(0)?.icon.toString()+"@2x.png").resize(50, 50).into(ivNowIcon);
                        tvDegree.text = (it.current?.feelsLike?.toInt()?.minus(273)).toString()

                        rvDaily.adapter = DailyAdapter().apply {
                            //submitList(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<WeatherConModel>, t: Throwable) {
                Log.d("deneme1 :", t.message.toString())
            }

        })
    }


    private fun getDeneme()
    {
        Log.d("deneme1 :", "asd")
        ApiClient.getApiService().getDeneme().enqueue(object : Callback<WeatherConModel>{
            override fun onResponse(call: Call<WeatherConModel>, response: Response<WeatherConModel>) {
                Log.d("deneme1 :", response.body().toString())

                if (response.isSuccessful) {
                    val everything = response.body()
                    everything?.let {
                        Log.d("deneme1 :", it.timezone.toString())
                    }
                }
            }

            override fun onFailure(call: Call<WeatherConModel>, t: Throwable) {
                Log.d("deneme1 :", t.message.toString())
            }

        })
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


}