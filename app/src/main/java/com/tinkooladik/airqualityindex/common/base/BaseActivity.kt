package com.tinkooladik.airqualityindex.common.base

import android.os.Bundle
import com.tinkooladik.airqualityindex.common.protocol.LayoutSettingsProtocol
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(),
    LayoutSettingsProtocol {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isSetAsContent()) setContentView(getLayoutId())
    }
}