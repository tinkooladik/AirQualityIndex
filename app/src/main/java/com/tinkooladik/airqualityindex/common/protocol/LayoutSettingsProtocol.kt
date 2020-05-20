package com.tinkooladik.airqualityindex.common.protocol

import androidx.annotation.LayoutRes

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class LayoutSettings(
    @LayoutRes val layoutId: Int = 0,
    val setWithoutBinding: Boolean = false
)

interface LayoutSettingsProtocol {

    fun getLayoutId(): Int = getAnnotation().layoutId

    fun isSetAsContent(): Boolean = getAnnotation().setWithoutBinding

    private fun getAnnotation(): LayoutSettings =
        javaClass.getAnnotation(LayoutSettings::class.java)
            ?: throw RuntimeException("Cannot find LayoutSettings annotation")
}