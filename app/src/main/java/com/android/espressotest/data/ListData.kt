package com.android.espressotest.data

import com.google.gson.annotations.SerializedName

data class ListData(
    @SerializedName("data")
    val `data`: List<Data>
)