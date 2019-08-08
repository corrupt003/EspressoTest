package com.android.espressotest.data

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("check_box")
    val check_box: Boolean,

    @SerializedName("image")
    val image: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("title")
    val title: String
)