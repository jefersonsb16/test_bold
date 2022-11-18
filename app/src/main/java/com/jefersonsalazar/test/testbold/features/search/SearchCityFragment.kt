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
import com.jefersonsalazar.test.testbold.features.search.adapter.SearchCityAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCityFragment : Fragment(), IClickItemCityListener {

    private var _binding: FragmentSearchCityBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel: SearchCityViewModel by viewModels()
    private val searchCityAdapter: SearchCityAdapter = SearchCityAdapter(this)

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
        state.searchResultsList?.let { cities ->
            searchCityAdapter.submitList(cities)
        }
    }

    private fun initView() {
        binding.recyclerViewSearchResults.apply {
            adapter = searchCityAdapter
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
        if (search.length >= 3) {
            searchViewModel.onSearchCity(search)
        }
    }

    override fun onItemClickListener(city: CityDomain) {
        searchViewModel.saveCityInRecentlyViewed(city)
        val navAction = SearchCityFragmentDirections.actionSearchCityFragmentToDetailSearchFragment(city.name)
        findNavController().navigate(navAction)
    }
}