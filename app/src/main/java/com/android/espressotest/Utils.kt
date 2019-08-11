package com.android.espressotest

import android.content.res.AssetManager
import java.io.InputStreamReader
import java.io.Reader

class Utils {
    companion object {
        fun getJsonReaderFromAssets(assets: AssetManager): Reader {
            val `is` = assets.open("list.json")
            return InputStreamReader(`is`, "UTF-8")
        }
    }
}