package com.tinkooladik.airqualityindex.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.View
import com.tinkooladik.airqualityindex.BR
import com.tinkooladik.airqualityindex.R
import com.tinkooladik.airqualityindex.common.LayoutSettings
import com.tinkooladik.airqualityindex.common.binding.BaseBindingFragment
import com.tinkooladik.airqualityindex.databinding.FragmentHomeBinding

@LayoutSettings(layoutId = R.layout.fragment_home)
class HomeFragment : BaseBindingFragment<FragmentHomeBinding, HomeViewModel>(), HomeErrorHandler {

    override fun getVar(): Int = BR.viewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appGraph.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}