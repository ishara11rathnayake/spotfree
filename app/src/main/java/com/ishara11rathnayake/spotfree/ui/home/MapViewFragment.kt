package com.ishara11rathnayake.spotfree.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ishara11rathnayake.spotfree.R
import com.ishara11rathnayake.spotfree.databinding.FragmentMapBinding
import com.ishara11rathnayake.spotfree.models.ParkingSlotsData

class MapViewFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private var mapFragment: SupportMapFragment? = null
    private var googleMap: GoogleMap? = null
    private var mapViewModel: MapViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val root: View = binding.root
        mapViewModel =
            ViewModelProvider(this)[MapViewModel::class.java]

        initiateMapFragment()

        mapViewModel!!.parkingData.observe(viewLifecycleOwner, Observer { slotsData ->
            addMarkers(slotsData)
        })

        filterData()

        binding.filterButton.setOnClickListener {
            filterData()
        }

        return root
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
        mapViewModel!!.setParkingStatus(if (isChecked) "Unoccupied" else null)
        mapViewModel!!.setRadius(radius)
    }

    private fun initiateMapFragment() {
        mapFragment = childFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment

        mapFragment?.getMapAsync {
            // check location permission and request if not granted
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
                return@getMapAsync
            }
            it.isMyLocationEnabled = true

            googleMap = it
        }
    }

    private fun addMarkers(slotsData: ParkingSlotsData) {
        // clear all markers
        googleMap?.clear()
        // animate camera to Melbourne
        googleMap?.animateCamera(
            com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
                LatLng(-37.8136, 144.9631),
                12f
            )
        )
        slotsData.records.map {
            val location = LatLng(it.fields.lat, it.fields.lon)
            googleMap?.addMarker(MarkerOptions().title(it.fields.status).position(location))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mapViewModel?.cancelJobs()
    }
}