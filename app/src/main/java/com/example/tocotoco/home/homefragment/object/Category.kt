package com.example.tocotoco.home.homefragment.`object`

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("createAt")
    @Expose
    val createAt: String?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("displayImage")
    @Expose
    val displayImage: String?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("modifiedAt")
    @Expose
    val modifiedAt: String?,
    @SerializedName("name")
    @Expose
    val name: String?
)