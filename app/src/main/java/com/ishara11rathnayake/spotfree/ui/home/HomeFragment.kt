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
import com.ishara11rathnayake.spotfree.databinding.FragmentHomeBinding
import com.ishara11rathnayake.spotfree.models.ParkingSlotsData

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var mapFragment: SupportMapFragment? = null
    private var googleMap: GoogleMap? = null
    private var homeViewModel: HomeViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        initiateMapFragment()


        homeViewModel!!.parkingData.observe(viewLifecycleOwner, Observer { slotsData ->
            addMarkers(slotsData)
        })

        // on checked listener for the switch
        binding.onlyUnoccupiedSwitch.setOnCheckedChangeListener { _, isChecked ->
            homeViewModel!!.setParkingStatus(if (isChecked) "Unoccupied" else null)
        }

        return root
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
        homeViewModel?.cancelJobs()
    }
}