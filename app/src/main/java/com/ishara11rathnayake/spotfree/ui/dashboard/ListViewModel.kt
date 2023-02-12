package com.ishara11rathnayake.spotfree.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ishara11rathnayake.spotfree.models.ParkingSlotsData
import com.ishara11rathnayake.spotfree.repository.Repository

class ListViewModel : ViewModel() {

    private val _parkingStatus: MutableLiveData<String?> = MutableLiveData()
    private val _radius: MutableLiveData<Int?> = MutableLiveData()

    // call getParkingData() method in the repository after two values are set
    val parkingData: LiveData<ParkingSlotsData> = Transformations
        .switchMap(_parkingStatus) { parkingStatus ->
            Transformations.switchMap(_radius) { radius ->
                Repository.getParkingData(parkingStatus, radius)
            }
        }

    fun setParkingStatus(parkingStatus: String?) {
        _parkingStatus.value = parkingStatus
    }

    fun setRadius(radius: Int?) {
        _radius.value = radius
    }

    fun cancelJobs(){
        Repository.cancelJobs()
    }
}