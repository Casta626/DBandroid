package com.example.db

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repo (
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String
)