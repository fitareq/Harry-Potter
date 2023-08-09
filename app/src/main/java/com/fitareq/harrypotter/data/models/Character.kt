package com.fitareq.harrypotter.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    @Expose
    val id: String?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("alternate_names")
    @Expose
    val alternateNames: ArrayList<String>,

    @SerializedName("species")
    @Expose
    val species: String?,

    @SerializedName("gender")
    @Expose
    val gender: String?,

    @SerializedName("house")
    @Expose
    val house: String?,

    @SerializedName("dateOfBirth")
    @Expose
    val dateOfBirth: String?,

    @SerializedName("yearOfBirth")
    @Expose
    val yearOfBirth: Int?,

    @SerializedName("wizard")
    @Expose
    val wizard: Boolean?,

    @SerializedName("ancestry")
    @Expose
    val ancestry: String?,

    @SerializedName("eyeColour")
    @Expose
    val eyeColour: String?,

    @SerializedName("hairColour")
    @Expose
    val hairColour: String?,

    @SerializedName("wand")
    @Expose
    val wand: Wand?,

    @SerializedName("patronus")
    @Expose
    val patronus: String?,

    @SerializedName("hogwartsStudent")
    @Expose
    val hogwartsStudent: Boolean?,

    @SerializedName("hogwartsStaff")
    @Expose
    val hogwartsStaff: Boolean?,

    @SerializedName("actor")
    @Expose
    val actor: String?,

    @SerializedName("alternate_actors")
    @Expose
    val alternateActors: ArrayList<String>,

    @SerializedName("alive")
    @Expose
    val alive: Boolean?,

    @SerializedName("image")
    @Expose
    val image: String?

)
