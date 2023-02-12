package com.ishara11rathnayake.spotfree.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ishara11rathnayake.spotfree.databinding.ParkingListItemBinding
import com.ishara11rathnayake.spotfree.models.ParkingSlotRecord
import com.ishara11rathnayake.spotfree.ui.dashboard.ListViewModel

class ParkingListViewAdapter(private val listViewModel: ListViewModel): RecyclerView.Adapter<ParkingListViewHolder>() {
    private var parkingList: List<ParkingSlotRecord> = emptyList()
    private var selectedPos = RecyclerView.NO_POSITION
    private lateinit var context: Context

    fun setParkingList(parkingList: List<ParkingSlotRecord>) {
        this.parkingList = parkingList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ParkingListItemBinding.inflate(inflater, parent, false)
        this.context = parent.context
        return ParkingListViewHolder(dataBinding, listViewModel)
    }

    override fun onBindViewHolder(holder: ParkingListViewHolder, position: Int) {
        holder.bind(parkingList[holder.layoutPosition])
        holder.itemView.isSelected = selectedPos == holder.layoutPosition
    }

    override fun getItemCount() = parkingList.size
}

