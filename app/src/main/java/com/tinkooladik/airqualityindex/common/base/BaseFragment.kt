package com.tinkooladik.airqualityindex.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tinkooladik.airqualityindex.common.LayoutSettingsProtocol
import com.tinkooladik.airqualityindex.di.AppGraph
import com.tinkooladik.airqualityindex.di.HasAppGraph
import com.tinkooladik.airqualityindex.util.inflate

abstract class BaseFragment : Fragment(), LayoutSettingsProtocol {

    lateinit var appGraph: AppGraph

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appGraph = (context.applicationContext as HasAppGraph).appGraph()
    }

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

    private fun setLayoutTitle() {
        val title = getLayoutTitle()
        if (title != 0) {
            setTitle(getString(title))
        }
    }

    open fun setTitle(title: String?) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }
}