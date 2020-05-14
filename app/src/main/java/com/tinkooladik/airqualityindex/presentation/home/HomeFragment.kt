package com.tinkooladik.airqualityindex.presentation.home

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.View
import com.tinkooladik.airqualityindex.BR
import com.tinkooladik.airqualityindex.R
import com.tinkooladik.airqualityindex.common.LayoutSettings
import com.tinkooladik.airqualityindex.common.adapter.SimpleAdapter
import com.tinkooladik.airqualityindex.common.binding.BaseBindingFragment
import com.tinkooladik.airqualityindex.databinding.FragmentHomeBinding
import com.tinkooladik.airqualityindex.util.initWithAdapter
import com.tinkooladik.airqualityindex.util.toast
import kotlinx.android.synthetic.main.fragment_home.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
@LayoutSettings(layoutId = R.layout.fragment_home)
class HomeFragment : BaseBindingFragment<FragmentHomeBinding, HomeViewModel>(), HomeErrorHandler {

    override fun getVar(): Int = BR.viewModel

    val adapter = SimpleAdapter<HomeStationVM>(R.layout.item_home_station, BR.station)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appGraph.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init adapter
        rvStations.initWithAdapter(adapter)
        observe(viewModel.items) { adapter.items = it }
        adapter.onItemClickListener = { context?.toast(it.name ?: "") }

        loadStationsWithPermissionCheck()
    }

    @NeedsPermission(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    fun loadStations() {
        viewModel.start()
    }

    @OnPermissionDenied(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    fun onLocationDenied() {
        //todo handle this
        loadStationsWithPermissionCheck()
    }
}