package com.tinkooladik.airqualityindex.common

import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.tinkooladik.airqualityindex.data.logError
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:defaultValue")
fun Spinner.setDefault(value: String) {
    for (i in 0 until adapter.count) {
        if (adapter.getItem(i).toString() == value) {
            setSelection(i)
            break
        }
    }
}

@BindingAdapter(value = ["bind:date", "bind:dateFormat", "bind:template"], requireAll = false)
fun TextView.bindDate(value: Long, dateFormat: String?, template: String?) {
    val formatValue = try {
        SimpleDateFormat(dateFormat ?: "d.MM.yyyy", Locale.getDefault()).format(value)
    } catch (e: ParseException) {
        logError("failed to parse date", e)
        ""
    }
    text = template?.let { String.format(it, formatValue) } ?: formatValue
}