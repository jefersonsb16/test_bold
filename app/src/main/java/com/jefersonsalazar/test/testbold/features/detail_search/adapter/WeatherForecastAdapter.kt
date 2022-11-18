package com.jefersonsalazar.test.testbold.features.detail_search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jefersonsalazar.test.domain.entities.ForecastDayDomain
import com.jefersonsalazar.test.testbold.R
import com.jefersonsalazar.test.testbold.databinding.ItemViewWeatherForecastBinding
import com.jefersonsalazar.test.testbold.features.bindImageUrl
import com.jefersonsalazar.test.testbold.features.getWeatherAverage

class WeatherForecastAdapter : RecyclerView.Adapter<WeatherForecastAdapter.ViewHolderForecast>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ForecastDayDomain>() {
        override fun areItemsTheSame(oldItem: ForecastDayDomain, newItem: ForecastDayDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ForecastDayDomain, newItem: ForecastDayDomain): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderForecast {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view_weather_forecast, parent, false)
        return ViewHolderForecast(view)
    }

    override fun onBindViewHolder(holder: ViewHolderForecast, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<ForecastDayDomain>) {
        differ.submitList(list)
    }

    inner class ViewHolderForecast(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemViewWeatherForecastBinding.bind(itemView)

        fun bind(forecast: ForecastDayDomain) {
            binding.textViewDateForecast.text = forecast.date
            binding.textViewConditionForecast.text = forecast.dayInfo.condition.description
            binding.textViewTempForecast.text = forecast.dayInfo.getWeatherAverage()

            binding.imageViewIconConditionForecast.bindImageUrl(
                forecast.dayInfo.condition.icon,
                R.drawable.weather_placeholder,
                R.drawable.weather_placeholder
            )
        }
    }

}