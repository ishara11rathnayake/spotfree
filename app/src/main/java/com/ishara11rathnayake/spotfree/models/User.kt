package com.ishara11rathnayake.spotfree.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (
    @Expose
    @SerializedName("email")
    val email: String? = null,

    @Expose
    @SerializedName("username")
    val username: String? = null,

    @Expose
    @SerializedName("image")
    val image: String? = null
)