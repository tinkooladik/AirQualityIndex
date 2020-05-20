package com.tinkooladik.airqualityindex.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment


fun Context.requestedPermissions(): Array<String> = packageManager
    .getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS)
    .requestedPermissions

fun Context.checkPermissions(permissions: Array<String>) =
    permissions.all {
        ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

fun Fragment.checkPermissions(
    permissions: Array<String>
) = context?.run {
    checkPermissions(permissions)
} ?: false