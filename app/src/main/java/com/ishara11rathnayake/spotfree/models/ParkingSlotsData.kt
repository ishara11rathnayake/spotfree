package com.ishara11rathnayake.spotfree.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ParkingSlotsData(
    @Expose
    @SerializedName("nhits")
    val nhits: Number? = 0,

    @Expose
    @SerializedName("records")
    val records: List<ParkingSlotRecord> = emptyList(),
)

data class ParkingSlotRecord (
    @Expose
    @SerializedName("recordid")
    val recordid: String? = null,

    @Expose
    @SerializedName("fields")
    val fields: ParkingSlotField,
)

data class ParkingSlotField (
    @Expose
    @SerializedName("last_updated")
    val last_updated: String? = null,

    @Expose
    @SerializedName("lon")
    val lon: Double,

    @Expose
    @SerializedName("lat")
    val lat: Double,

    @Expose
    @SerializedName("status")
    val status: String? = null,
)