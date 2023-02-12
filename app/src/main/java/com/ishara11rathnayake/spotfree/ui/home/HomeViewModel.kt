package com.ishara11rathnayake.spotfree.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ishara11rathnayake.spotfree.models.ParkingSlotsData
import com.ishara11rathnayake.spotfree.repository.Repository

class HomeViewModel : ViewModel() {


    private val _parkingStatus: MutableLiveData<String?> = MutableLiveData()

    val parkingData: LiveData<ParkingSlotsData> = Transformations
        .switchMap(_parkingStatus){
            Repository.getParkingData(it)
        }

    fun setParkingStatus(parkingStatus: String?) {
        _parkingStatus.value = parkingStatus
    }

    fun cancelJobs(){
        Repository.cancelJobs()
    }
}