package com.example.fragmenttask.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


class RetroResponse {
    @SerializedName("responseCode")
    var responseCode: String? = null

    @SerializedName("responseMsg")
    var responseMsg: String? = null

    @SerializedName("priceUnit")
    var priceUnit: String? = null

    @SerializedName("devices")
    var devices: ArrayList<Devices> = ArrayList()
}

@Parcelize
data class Devices(
    @SerializedName("productId")
    var productId: String? = null,

    @SerializedName("brand")
    var brand: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("startingPrice")
    var startingPrice: String? = null,

    @SerializedName("startingPoints")
    var startingPoints: String? = null,

    @SerializedName("vatPercentage")
    var vatPercentage: String? = null,

    @SerializedName("imageUrl")
    var imageUrl: String? = null
): Parcelable