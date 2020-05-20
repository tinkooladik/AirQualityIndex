package com.tinkooladik.airqualityindex.presentation.station_details

import androidx.navigation.fragment.navArgs
import com.tinkooladik.airqualityindex.BR
import com.tinkooladik.airqualityindex.R
import com.tinkooladik.airqualityindex.common.LayoutSettings
import com.tinkooladik.airqualityindex.common.binding.BaseBindingFragment
import com.tinkooladik.airqualityindex.databinding.FragmentStationDetailsBinding
import com.tinkooladik.airqualityindex.presentation.home.HomeErrorHandler

@LayoutSettings(layoutId = R.layout.fragment_station_details)
class StationDetailsFragment :
    BaseBindingFragment<FragmentStationDetailsBinding, StationDetailsViewModel>(),
    HomeErrorHandler {

    override fun getVar(): Int = BR.viewModel

    private val args: StationDetailsFragmentArgs by navArgs()

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loadStationData(args.stationId)
    }
}