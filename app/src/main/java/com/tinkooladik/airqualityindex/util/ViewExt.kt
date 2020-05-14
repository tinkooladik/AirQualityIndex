package com.tinkooladik.airqualityindex.util

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun Context.dpToPx(dp: Int) = (dp * resources.displayMetrics.density).toInt()

fun Context.pxToDp(px: Int) = (px / resources.displayMetrics.density).toInt()

fun ViewGroup.inflate(@LayoutRes layout: Int, attach: Boolean = false): View =
    LayoutInflater.from(this.context).inflate(layout, this, attach)

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, this.resources.getText(resId), duration).show()
}

fun RecyclerView.initWithAdapter(
    adapter: RecyclerView.Adapter<*>, layoutManager: LinearLayoutManager? = null,
    @DrawableRes dividerRes: Int? = null
) {

    setLayoutManager(layoutManager ?: LinearLayoutManager(context))
    this.adapter = adapter

    if (dividerRes != null) {
        addItemDecoration(getDivider(context, dividerRes, layoutManager))
    }
}

private fun getDivider(
    context: Context, @DrawableRes dividerRes: Int,
    layoutManager: LinearLayoutManager? = null
): DividerItemDecoration {
    val divider =
        DividerItemDecoration(context, layoutManager?.orientation ?: LinearLayout.VERTICAL)
    divider.setDrawable(ContextCompat.getDrawable(context, dividerRes) ?: BitmapDrawable())
    return divider
}