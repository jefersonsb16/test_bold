package com.jefersonsalazar.test.testbold.features.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.testbold.R
import com.jefersonsalazar.test.testbold.databinding.ItemViewRecentSearchesBinding

class RecentSearchesAdapter(
    private val listener: IClickItemCityListener
) : RecyclerView.Adapter<RecentSearchesAdapter.ViewHolderRecentCity>() {

    private val diffCallback = object : DiffUtil.ItemCallback<CityDomain>() {
        override fun areItemsTheSame(oldItem: CityDomain, newItem: CityDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CityDomain, newItem: CityDomain): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecentCity {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view_recent_searches, parent, false)
        return ViewHolderRecentCity(view)
    }

    override fun onBindViewHolder(holder: ViewHolderRecentCity, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<CityDomain>) {
        differ.submitList(list)
    }

    inner class ViewHolderRecentCity(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemViewRecentSearchesBinding.bind(itemView)

        fun bind(city: CityDomain) {
            binding.textViewNameCityRecentSearches.text = city.name
            itemView.setOnClickListener { listener.onItemClickListener(city) }
        }
    }

}