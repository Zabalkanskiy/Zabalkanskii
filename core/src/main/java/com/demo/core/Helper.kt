package com.demo.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Fragment.hasNetwork(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}

fun AppCompatActivity.noStatusBar(need: Boolean) {
    if (need) {
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    } else {
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
    }
}

fun FragmentActivity.noStatusBar(need: Boolean) {
    if (need) {
        getWindow().setFlags(
            android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    } else {
        getWindow().setFlags(
            android.view.WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            android.view.WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
    }
}