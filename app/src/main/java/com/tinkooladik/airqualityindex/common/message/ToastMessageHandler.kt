package com.tinkooladik.airqualityindex.common.message

import android.content.Context
import com.tinkooladik.airqualityindex.domain.weak
import com.tinkooladik.airqualityindex.util.ext.toast

class ToastMessageHandler constructor(context: Context?) : MessageHandler {

    private val context by weak(context)

    override fun showMessage(message: String?) {
        message?.let { context?.toast(message) }
    }

    override fun showError(message: String?) {
        message?.let { context?.toast(message) }
    }
}