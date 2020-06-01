package com.tinkooladik.airqualityindex.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tinkooladik.airqualityindex.common.protocol.LayoutSettingsProtocol
import com.tinkooladik.airqualityindex.util.ext.inflate
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment(),
    LayoutSettingsProtocol {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutId = getLayoutId()
        return if (layoutId != 0 || isSetAsContent()) {
            container?.inflate(getLayoutId())
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }
}