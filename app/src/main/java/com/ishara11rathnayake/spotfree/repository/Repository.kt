package com.ishara11rathnayake.spotfree.repository

import androidx.lifecycle.LiveData
import com.ishara11rathnayake.spotfree.api.RetrofitBuilder
import com.ishara11rathnayake.spotfree.models.ParkingSlotsData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {
    var job: CompletableJob? = null

    fun getParkingData(parkingStatus: String?): LiveData<ParkingSlotsData> {
        job = Job()
        return object: LiveData<ParkingSlotsData>() {
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        println("parkingStatus: $parkingStatus")
                        try {
                            val parkingData: ParkingSlotsData = if (parkingStatus == null) {
                                RetrofitBuilder.apiService.getParkingSlots()
                            } else {
                                RetrofitBuilder.apiService.filterParkingData(parkingStatus)
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

    fun filterParkingData(parkingStatus: String?): LiveData<ParkingSlotsData> {
        job = Job()
        return object: LiveData<ParkingSlotsData>() {
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val parkingData = RetrofitBuilder.apiService.filterParkingData(parkingStatus)
                        withContext(Main) {
                            value = parkingData
                            theJob.complete()
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