package com.jefersonsalazar.test.testbold.features.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jefersonsalazar.test.domain.entities.CityDomain
import com.jefersonsalazar.test.testbold.databinding.FragmentSearchCityBinding
import com.jefersonsalazar.test.testbold.features.launchAndCollect
import com.jefersonsalazar.test.testbold.features.search.adapter.IClickItemCityListener
import com.jefersonsalazar.test.testbold.features.search.adapter.RecentSearchesAdapter
import com.jefersonsalazar.test.testbold.features.search.adapter.SearchCityAdapter
import dagger.hilt.android.AndroidEntryPoint

const val MIN_LENGTH_TEXT_INPUT_FOR_SEARCH = 3

@AndroidEntryPoint
class SearchCityFragment : Fragment(), IClickItemCityListener {

    private var _binding: FragmentSearchCityBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchCityViewModel by viewModels()
    private val searchCityAdapter: SearchCityAdapter = SearchCityAdapter(this)
    private val recentSearchesAdapter: RecentSearchesAdapter = RecentSearchesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservables()
    }

    private fun initObservables() {
        viewLifecycleOwner.launchAndCollect(searchViewModel.state) { state ->
            updateUiWithState(state)
        }
    }

    private fun updateUiWithState(state: SearchCityViewModel.UIState) {
        binding.searchProgress.isVisible = state.loading
        state.showMessageStartSearch?.let { isVisible ->
            binding.textViewMessageStartSearch.isVisible = isVisible
        }
        state.searchResultsList?.let { cities ->
            searchCityAdapter.submitList(cities)
        }
        state.recentSearchesList?.let { recentCities ->
            recentSearchesAdapter.submitList(recentCities)
            binding.textViewRecentSearches.isVisible = recentCities.isNotEmpty()
            binding.recyclerViewRecentSearches.isVisible = recentCities.isNotEmpty()
        }
        state.showMessageNotSearchResults?.let { isVisible ->
            binding.backGroundViewNotSearchResults.isVisible = isVisible
            binding.imageViewNotSearchResults.isVisible = isVisible
            binding.textViewNotSearchResults.isVisible = isVisible
        }
    }

    private fun initView() {
        binding.recyclerViewSearchResults.apply {
            adapter = searchCityAdapter
        }
        binding.recyclerViewRecentSearches.apply {
            adapter = recentSearchesAdapter
        }
        binding.edtSearchCities.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                goToSearchCity(p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun goToSearchCity(search: String) {
        if (search.length >= MIN_LENGTH_TEXT_INPUT_FOR_SEARCH) {
            searchViewModel.onSearchCity(search)
        } else {
            searchViewModel.clearCurrentSearchResults()
        }
    }

    override fun onItemClickListener(city: CityDomain) {
        searchViewModel.saveCityInRecentlyViewed(city)
        val navAction = SearchCityFragmentDirections.actionSearchCityFragmentToDetailSearchFragment(city.name)
        findNavController().navigate(navAction)
    }
}