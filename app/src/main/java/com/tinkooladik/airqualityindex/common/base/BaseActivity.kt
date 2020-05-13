package com.tinkooladik.airqualityindex.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tinkooladik.airqualityindex.common.LayoutSettingsProtocol
import com.tinkooladik.airqualityindex.di.AppGraph
import com.tinkooladik.airqualityindex.di.HasAppGraph

abstract class BaseActivity : AppCompatActivity(),
    LayoutSettingsProtocol {

    lateinit var appGraph: AppGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isSetAsContent()) setContentView(getLayoutId())

        appGraph = (application as HasAppGraph).appGraph()
    }
}