package com.tinkooladik.airqualityindex.presentation.home

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.tinkooladik.airqualityindex.BR
import com.tinkooladik.airqualityindex.R
import com.tinkooladik.airqualityindex.common.LayoutSettings
import com.tinkooladik.airqualityindex.common.adapter.SimpleAdapter
import com.tinkooladik.airqualityindex.common.binding.BaseBindingFragment
import com.tinkooladik.airqualityindex.databinding.FragmentHomeBinding
import com.tinkooladik.airqualityindex.util.initWithAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
@LayoutSettings(layoutId = R.layout.fragment_home)
class HomeFragment : BaseBindingFragment<FragmentHomeBinding, HomeViewModel>(), HomeErrorHandler {

    override fun getVar(): Int = BR.viewModel

    val adapter = SimpleAdapter<StationVM>(R.layout.item_home_station, BR.station)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appGraph.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvStations.initWithAdapter(adapter)
        observe(viewModel.items) {
            adapter.items = it
            rvStations.scrollToPosition(0)
        }
        adapter.onItemClickListener = { station ->
            findNavController().navigate(
                ActionFragmentHomeToFragmentDetails(station.id ?: 0)
            )
        }

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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }
}

data class ActionFragmentHomeToFragmentDetails(
    val stationId: Int
) : NavDirections {

    override fun getActionId(): Int = R.id.action_fragmentHome_to_fragmentDetails

    override fun getArguments(): Bundle {
        return Bundle().apply { putInt("stationId", stationId) }
    }
}