package com.tinkooladik.airqualityindex.common

import android.widget.Spinner
import androidx.databinding.BindingAdapter

@BindingAdapter("app:defaultValue")
fun Spinner.setDefault(value: String) {
    for (i in 0 until adapter.count) {
        if (adapter.getItem(i).toString() == value) {
            setSelection(i)
            break
        }
    }
}