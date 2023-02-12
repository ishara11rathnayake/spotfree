package com.ishara11rathnayake.spotfree.api

import com.ishara11rathnayake.spotfree.models.ParkingSlotsData
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("search/?dataset=on-street-parking-bay-sensors&q=&sort=last_updated&facet=status&facet=parking_zone&facet=last_updated")
    suspend fun getParkingSlots(): ParkingSlotsData

    @GET("search/?dataset=on-street-parking-bay-sensors&q=&sort=last_updated&facet=status&facet=parking_zone&facet=last_updated")
    suspend fun filterByStatus(
        @Query("refine.status") status: String?,
    ): ParkingSlotsData

    @GET("search/?dataset=on-street-parking-bay-sensors&q=&sort=last_updated&facet=status&facet=parking_zone&facet=last_updated")
    suspend fun filterByRadius(
        @Query("geofilter.distance") radius: String?,
    ): ParkingSlotsData

    @GET("search/?dataset=on-street-parking-bay-sensors&q=&sort=last_updated&facet=status&facet=parking_zone&facet=last_updated")
    suspend fun filterParkingData(
        @Query("refine.status") status: String?,
        @Query("geofilter.distance") radius: String?,
    ): ParkingSlotsData
}