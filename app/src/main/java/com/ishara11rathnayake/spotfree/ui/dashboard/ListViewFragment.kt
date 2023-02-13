package com.ishara11rathnayake.spotfree.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishara11rathnayake.spotfree.adapter.ParkingListViewAdapter
import com.ishara11rathnayake.spotfree.databinding.FragmentListBinding

class ListViewFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var listViewModel: ListViewModel? = null
    private lateinit var adapter: ParkingListViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // initial API Call
        filterData()

        binding.filterButton.setOnClickListener {
            filterData()
        }

        setupAdapter()

        listViewModel!!.parkingData.observe(viewLifecycleOwner, Observer { slotsData ->
            println("Data: ${slotsData.records}")
            binding.progressBar.visibility = View.GONE
            adapter.setParkingList(slotsData.records)
        })

        return root
    }

    private fun setupAdapter() {
        if (listViewModel != null) {
            adapter = ParkingListViewAdapter(listViewModel!!)
            binding.parkingDataRecyclerView.layoutManager = LinearLayoutManager(activity)
            binding.parkingDataRecyclerView.addItemDecoration(DividerItemDecoration(activity, 0))
            binding.parkingDataRecyclerView.adapter = adapter
        }
    }

    private fun filterData () {
        // get checked status of the show only unoccupied switch
        val isChecked = binding.onlyUnoccupiedSwitch.isChecked
        // get radius value
        val radius = if (binding.radiusEditText.text.toString() == "") {
            null
        } else {
            binding.radiusEditText.text.toString().toInt() * 1000
        }

        // set two values to the view model
        listViewModel!!.setParkingStatus(if (isChecked) "Unoccupied" else null)
        listViewModel!!.setRadius(radius)

        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        listViewModel!!.cancelJobs()
    }
}