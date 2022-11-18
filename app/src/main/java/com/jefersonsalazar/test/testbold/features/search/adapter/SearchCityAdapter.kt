package com.jefersonsalazar.test.testbold.features.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.testbold.R
import com.jefersonsalazar.test.testbold.databinding.ItemViewSearchCityBinding

class SearchCityAdapter(
    private val listener: IClickItemCityListener
) : RecyclerView.Adapter<SearchCityAdapter.ViewHolderCity>() {

    private val diffCallback = object : DiffUtil.ItemCallback<CityDomain>() {
        override fun areItemsTheSame(oldItem: CityDomain, newItem: CityDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CityDomain, newItem: CityDomain): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCity {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view_search_city, parent, false)
        return ViewHolderCity(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCity, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<CityDomain>) {
        differ.submitList(list)
    }

    inner class ViewHolderCity(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemViewSearchCityBinding.bind(itemView)

        fun bind(city: CityDomain) {
            binding.textViewInformationCity.text = "${city.name}, ${city.country}"
            itemView.setOnClickListener { listener.onItemClickListener(city, false) }
        }
    }

}