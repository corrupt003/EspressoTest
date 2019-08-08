package com.android.espressotest.extensions

import android.com.espressotest.R
import androidx.annotation.DrawableRes

class Extensions {
    /**
     * Convert JSON `image` string to desired drawable resource id,
     * default may return `R.drawable.ic_launcher_foreground`.
     */
    @DrawableRes
    fun String.toImageResId(): Int {
        return when (this) {
            "ic_android_32dp" -> R.drawable.ic_android_32dp
            "ic_bug_32dp" -> R.drawable.ic_bug_32dp
            "ic_smile_32dp" -> R.drawable.ic_smile_32dp
            else -> R.drawable.ic_launcher_foreground
        }
    }
}