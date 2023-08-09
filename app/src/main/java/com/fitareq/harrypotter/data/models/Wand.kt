package com.fitareq.harrypotter.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wand(
    @SerializedName("wood")
    @Expose
    val wood: String?,
    @SerializedName("core")
    @Expose
    val core: String?,
    @SerializedName("length")
    @Expose
    val length: Double?
)
