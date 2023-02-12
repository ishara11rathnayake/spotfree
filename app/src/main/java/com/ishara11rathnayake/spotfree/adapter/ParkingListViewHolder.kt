package com.ishara11rathnayake.spotfree.adapter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ishara11rathnayake.spotfree.databinding.ParkingListItemBinding
import com.ishara11rathnayake.spotfree.models.ParkingSlotRecord
import com.ishara11rathnayake.spotfree.ui.dashboard.ListViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ParkingListViewHolder(private val binding: ParkingListItemBinding, private val listViewModel: ListViewModel):
    RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(data: ParkingSlotRecord){
        if (data.fields.status == "Unoccupied") {
            binding.locationTextView.setTextColor(binding.root.context.getColor(android.R.color.holo_green_dark))
        } else {
            binding.locationTextView.setTextColor(binding.root.context.getColor(android.R.color.holo_red_dark))
        }
        binding.locationTextView.text = data.fields.status
        binding.parkingZoneTextView.text = data.fields.parkingZone.toString()

        // convert iso date-time string into readable date-time string
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
        val readableString = LocalDateTime.parse(data.fields.lastUpdated, formatter).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        binding.lastUpdatedTextView.text = readableString
    }
}