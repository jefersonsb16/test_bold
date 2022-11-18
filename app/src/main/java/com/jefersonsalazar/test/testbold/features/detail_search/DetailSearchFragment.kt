package com.jefersonsalazar.test.testbold.features.detail_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.jefersonsalazar.test.testbold.databinding.FragmentDetailSearchBinding
import com.jefersonsalazar.test.testbold.features.ShowErrorFactory
import com.jefersonsalazar.test.testbold.features.launchAndCollect
import com.jefersonsalazar.test.testbold.features.splitAndGetJustName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSearchFragment : Fragment() {

    private val detailSearchViewModel: DetailSearchViewModel by viewModels()
    private val safeArgs: DetailSearchFragmentArgs by navArgs()
    private var _binding: FragmentDetailSearchBinding? = null
    private val showErrorFactory = ShowErrorFactory()
    private val binding get() = _binding!!

    private var locationName = ""

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
    }

    private fun initView() {
        val navHost = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(binding.toolbarDetailSearch, navHost)

        binding.textViewTitleDetailSearch.text = locationName
        detailSearchViewModel.onGetDetailCitySearch(locationName.splitAndGetJustName())
    }

    private fun initObservables() {
        viewLifecycleOwner.launchAndCollect(detailSearchViewModel.state) { state ->
            updateUiWithState(state)
        }
    }

    private fun updateUiWithState(state: DetailSearchViewModel.UIState) {
        binding.searchProgress.isVisible = state.loading
        state.error?.let { error ->
            showErrorFactory.getDialog(requireContext(), error).show()
            detailSearchViewModel.resetState()
        }
    }
}