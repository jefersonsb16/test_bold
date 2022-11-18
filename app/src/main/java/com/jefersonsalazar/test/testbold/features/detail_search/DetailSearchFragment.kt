package com.jefersonsalazar.test.testbold.features.detail_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.jefersonsalazar.test.domain.entities.CurrentWeatherDomain
import com.jefersonsalazar.test.testbold.R
import com.jefersonsalazar.test.testbold.databinding.FragmentDetailSearchBinding
import com.jefersonsalazar.test.testbold.features.ShowErrorFactory
import com.jefersonsalazar.test.testbold.features.bindImageUrl
import com.jefersonsalazar.test.testbold.features.detail_search.adapter.WeatherForecastAdapter
import com.jefersonsalazar.test.testbold.features.launchAndCollect
import com.jefersonsalazar.test.testbold.features.splitAndGetJustName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSearchFragment : Fragment() {

    private val detailSearchViewModel: DetailSearchViewModel by viewModels()
    private val safeArgs: DetailSearchFragmentArgs by navArgs()
    private var _binding: FragmentDetailSearchBinding? = null
    private val weatherForecastAdapter = WeatherForecastAdapter()
    private val showErrorFactory = ShowErrorFactory()
    private val binding get() = _binding!!

    private var locationName = ""
    private var cityId: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getParamsFragment()
        initView()
        initObservables()
    }

    private fun getParamsFragment() {
        locationName = safeArgs.locationText
        cityId = safeArgs.cityId
    }

    private fun initView() {
        val navHost = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(binding.toolbarDetailSearch, navHost)

        binding.textViewTitleDetailSearch.text = locationName
        detailSearchViewModel.onGetDetailCitySearch(locationName.splitAndGetJustName())

        binding.recyclerViewWeatherForecast.apply {
            adapter = weatherForecastAdapter
        }
        binding.fabRemoveCityFromLocal.setOnClickListener {
            detailSearchViewModel.onRemoveCity(cityId)
        }
        binding.fabRemoveCityFromLocal.isVisible = safeArgs.showBtnRemoveCity
    }

    private fun initObservables() {
        viewLifecycleOwner.launchAndCollect(detailSearchViewModel.state) { state ->
            updateUiWithState(state)
        }
    }

    private fun updateUiWithState(state: DetailSearchViewModel.UIState) {
        binding.searchProgress.isVisible = state.loading
        state.error?.let { error ->
            binding.includeNotResultsDetailCity.imageViewNotSearchResults.isVisible = true
            binding.includeNotResultsDetailCity.textViewNotSearchResults.isVisible = true
            binding.textViewTitleWeatherForecast.isVisible = false
            binding.imageviewIconCurrentWeatherCondition.isVisible = false
            binding.textViewCurrentWeatherCondition.isVisible = false
            binding.textViewCurrentWeatherTemp.isVisible = false
            binding.recyclerViewWeatherForecast.isVisible = false

            showErrorFactory.getDialog(requireContext(), error).show()
            detailSearchViewModel.resetState()
        }
        state.detailCity?.let { detailCity ->
            loadCurrentWeatherInformation(detailCity.currentWeather)
            weatherForecastAdapter.submitList(detailCity.weatherForecast.forecastDay)
            binding.textViewTitleWeatherForecast.isVisible = true
            binding.recyclerViewWeatherForecast.isVisible = true
        }
        state.removedCity?.let {
            Toast.makeText(requireContext(), getString(R.string.success_removed_city_text), Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
        }
    }

    private fun loadCurrentWeatherInformation(currentWeather: CurrentWeatherDomain) {
        binding.imageviewIconCurrentWeatherCondition.isVisible = true
        binding.textViewCurrentWeatherCondition.isVisible = true
        binding.textViewCurrentWeatherTemp.isVisible = true

        binding.textViewCurrentWeatherCondition.text = currentWeather.condition.description
        binding.textViewCurrentWeatherTemp.text = "${currentWeather.tempCelsius}º"
        binding.imageviewIconCurrentWeatherCondition.bindImageUrl(
            currentWeather.condition.icon,
            R.drawable.weather_placeholder,
            R.drawable.weather_placeholder
        )
    }
}