package com.akdagberkhan.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akdagberkhan.weatherapp.R
import com.akdagberkhan.weatherapp.model.WeatherConModel
import com.squareup.picasso.Picasso

class DailyAdapter : androidx.recyclerview.widget.ListAdapter<WeatherConModel, DailyAdapter.DailyViewHolder>(DailyDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_daily, parent, false)
        return DailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DailyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvDate = view.findViewById<TextView>(R.id.tvDateDaily)
        private val ivIcon = view.findViewById<ImageView>(R.id.ivDaily)
        private val tvDegree = view.findViewById<TextView>(R.id.tvDegreeDaily)
        fun bind(weather: WeatherConModel) {
            tvDate.text = weather.daily?.get(0)?.dt.toString()
            Picasso.get().load("http://openweathermap.org/img/wn/"+weather.current?.weather?.get(0)?.icon.toString()+"@2x.png").resize(50, 50).into(ivIcon);

        }
    }

    class DailyDiffUtil : DiffUtil.ItemCallback<WeatherConModel>() {
        override fun areItemsTheSame(oldItem: WeatherConModel, newItem: WeatherConModel): Boolean {
            return oldItem.timezone == newItem.timezone
        }

        override fun areContentsTheSame(oldItem: WeatherConModel, newItem: WeatherConModel): Boolean {
            return oldItem == newItem
        }
    }
}