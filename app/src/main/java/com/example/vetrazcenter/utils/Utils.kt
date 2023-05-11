package com.example.vetrazcenter.utils

import android.os.Build
import android.os.Bundle
import java.io.Serializable

object Utils {
    inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= 33 -> getSerializable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializable(key) as? T
    }

}