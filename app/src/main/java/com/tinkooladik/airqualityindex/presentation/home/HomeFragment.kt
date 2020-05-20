package com.tinkooladik.airqualityindex.presentation.home

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.tinkooladik.airqualityindex.BR
import com.tinkooladik.airqualityindex.R
import com.tinkooladik.airqualityindex.common.LayoutSettings
import com.tinkooladik.airqualityindex.common.adapter.SimpleAdapter
import com.tinkooladik.airqualityindex.common.binding.BaseBindingFragment
import com.tinkooladik.airqualityindex.databinding.FragmentHomeBinding
import com.tinkooladik.airqualityindex.util.checkPermissions
import com.tinkooladik.airqualityindex.util.initWithAdapter
import kotlinx.android.synthetic.main.fragment_home.*

private val requiredPermissions = arrayOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

@LayoutSettings(layoutId = R.layout.fragment_home)
class HomeFragment : BaseBindingFragment<FragmentHomeBinding, HomeViewModel>(), HomeErrorHandler {

    override fun getVar(): Int = BR.viewModel

    val adapter = SimpleAdapter<StationVM>(R.layout.item_home_station, BR.station)

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
    }

    override fun onResume() {
        super.onResume()

        val permissionsGranted = checkPermissions(requiredPermissions)
        viewModel.permissionsGranted(permissionsGranted)

        if (!permissionsGranted) {
            ActivityCompat.requestPermissions(requireActivity(), requiredPermissions, 0)
        }
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