package com.tinkooladik.airqualityindex.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class SimpleAdapter<VM : ObservableListItem>(
    private val holderLayout: Int,
    private val variableId: Int
) : BaseAdapter<VM, SimpleAdapter<VM>.SimpleBindingHolder<VM>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleBindingHolder<VM> {
        val v = LayoutInflater.from(parent.context).inflate(holderLayout, parent, false)
        return SimpleBindingHolder(v)
    }

    inner class SimpleBindingHolder<VM : ObservableListItem>(v: View) :
        BaseViewHolder<VM>(v, variableId)
}