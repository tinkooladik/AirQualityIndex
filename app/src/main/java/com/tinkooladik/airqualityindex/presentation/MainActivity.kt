package com.tinkooladik.airqualityindex.presentation

import android.os.Bundle
import com.tinkooladik.airqualityindex.R
import com.tinkooladik.airqualityindex.common.LayoutSettings
import com.tinkooladik.airqualityindex.common.base.BaseActivity

@LayoutSettings(R.layout.activity_main, setWithoutBinding = true)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appGraph.inject(this)
    }
}
