package com.tinkooladik.airqualityindex.common.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.tinkooladik.airqualityindex.common.base.BaseFragment
import com.tinkooladik.airqualityindex.common.base.BaseViewModel
import com.tinkooladik.airqualityindex.common.message.ErrorHandler
import com.tinkooladik.airqualityindex.common.message.MessageHandler
import com.tinkooladik.airqualityindex.common.message.ToastMessageHandler
import com.tinkooladik.airqualityindex.common.protocol.LiveDataObserveProtocol
import com.tinkooladik.airqualityindex.common.protocol.PermissionsProtocol
import com.tinkooladik.airqualityindex.di.ViewModelFactory
import com.tinkooladik.airqualityindex.util.checkPermissions
import javax.inject.Inject

abstract class BaseBindingFragment<B : ViewDataBinding, VM : BaseViewModel> :
    BaseFragment(),
    LiveDataObserveProtocol,
    PermissionsProtocol, ErrorHandler {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewModel: VM
    lateinit var binding: B

    open fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModel.javaClass)
        viewModel.initViewModel()
    }

    open fun getMessageHandler(): MessageHandler = ToastMessageHandler(context)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(getVar(), viewModel)
        initMessageHandlers()
    }

    override fun onResume() {
        super.onResume()
        binding.executePendingBindings()
        checkPermissions()
    }

    protected abstract fun getVar(): Int

    private fun initMessageHandlers(vm: VM = viewModel) {
        observe(vm.error) { handleError(it) { getMessageHandler().showError(it) } }
    }

    private fun checkPermissions() {
        getRequiredPermissions()?.let { requiredPermissions ->
            val permissionsGranted = checkPermissions(requiredPermissions)
            viewModel.permissionsGranted(permissionsGranted)

            if (!permissionsGranted) {
                ActivityCompat.requestPermissions(
                    requireActivity(), requiredPermissions, 0
                )
            }
        }
    }
}