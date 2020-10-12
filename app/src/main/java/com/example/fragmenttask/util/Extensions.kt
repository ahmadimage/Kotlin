package com.example.fragmenttask.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}


fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun isNetworkAvailable(context: Context?): Boolean {
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}
