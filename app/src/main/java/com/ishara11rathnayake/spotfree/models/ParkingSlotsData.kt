package com.ishara11rathnayake.spotfree.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

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
    @SerializedName("lon")
    val lon: Double,

    @Expose
    @SerializedName("lat")
    val lat: Double,

    @Expose
    @SerializedName("status")
    val status: String? = null,

    @Expose
    @SerializedName("parking_zone")
    val parkingZone: Number,

    @Expose
    @SerializedName("last_updated")
    val lastUpdated: String? = null,

)