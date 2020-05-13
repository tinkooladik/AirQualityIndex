package com.tinkooladik.airqualityindex.common.message


interface MessageHandler {

    fun showMessage(message: String?)

    fun showError(message: String?)
}