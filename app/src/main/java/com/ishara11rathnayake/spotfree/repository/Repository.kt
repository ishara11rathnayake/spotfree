package com.ishara11rathnayake.spotfree.repository

import androidx.lifecycle.LiveData
import com.ishara11rathnayake.spotfree.api.RetrofitBuilder
import com.ishara11rathnayake.spotfree.models.ParkingSlotsData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {
    var job: CompletableJob? = null

    fun getParkingData(parkingStatus: String?, radius: Int?): LiveData<ParkingSlotsData> {
        job = Job()

        return object: LiveData<ParkingSlotsData>() {
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        try {
                            val parkingData: ParkingSlotsData = if (parkingStatus == null && radius == null) {
                                RetrofitBuilder.apiService.getParkingSlots()
                            } else if (parkingStatus != null && radius == null) {
                                RetrofitBuilder.apiService.filterByStatus(parkingStatus)
                            } else if (parkingStatus == null) {
                                RetrofitBuilder.apiService.filterByRadius("-37.8136, 144.9631, $radius")
                            } else {
                                RetrofitBuilder.apiService.filterParkingData(parkingStatus, "-37.8136, 144.9631, $radius")
                            }
                            withContext(Main) {
                                value = parkingData
                                theJob.complete()
                            }
                        } catch (e: Exception) {
                            println("Error: ${e.message}")
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}