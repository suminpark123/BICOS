package com.dbtechprojects.photonotes.screens.client

import com.google.gson.annotations.SerializedName

data class Users2(
    @SerializedName("names")
    val names : String,

    @SerializedName("number2")
    val number2 : String,
)
