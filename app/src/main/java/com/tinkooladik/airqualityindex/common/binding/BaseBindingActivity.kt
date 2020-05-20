package com.tinkooladik.airqualityindex.common.binding

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tinkooladik.airqualityindex.common.base.BaseActivity
import com.tinkooladik.airqualityindex.common.base.BaseViewModel
import com.tinkooladik.airqualityindex.common.protocol.LiveDataObserveProtocol
import javax.inject.Inject

abstract class BaseBindingActivity<B : ViewDataBinding, VM : BaseViewModel> : BaseActivity(),
    LiveDataObserveProtocol {

    private lateinit var binding: B

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
    }

    protected abstract fun getVar(): Int

    private fun bind() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.setVariable(getVar(), viewModel)
        binding.executePendingBindings()
    }
}