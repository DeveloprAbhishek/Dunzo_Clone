package com.example.dunzoclone.Activities

import android.location.Address
import android.location.Geocoder
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.dunzoclone.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import java.util.*
import com.google.android.gms.maps.model.PolylineOptions




class OrderDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var lat: Double = 28.7041
    private var long: Double = 77.1025
    private var destinationLat: Double = 29.5815
    private var destinationLong: Double = 74.3294
    private var start: LatLng? = null
    private var end: LatLng? = null

    private lateinit var geocoder: Geocoder
    private lateinit var address: List<Address>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        geocoder = Geocoder(this, Locale.getDefault())
        locationLatLang()
        mapFragment()
    }


    private fun mapFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun locationLatLang() {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        val locationListener = LocationListener { location ->
            lat = location.latitude
            long = location.longitude
            address = geocoder.getFromLocation(lat, long, 1)
            start = LatLng(lat, long)
            end = LatLng(destinationLat, destinationLong)
            Log.d("abhishek", address.toString())
            mapFragment()
            Log.d("abhishek", "Latitute: $lat ; Longitute: $long")
        }

        try {
            locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch (ex:SecurityException) {
            Toast.makeText(applicationContext, "No Location here", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLong = LatLng(lat, long)
        var destinationLatLong = LatLng(destinationLat, destinationLong)
        googleMap.addMarker(
            MarkerOptions()
                .position(latLong)
                .title("Current Address"))

        googleMap.addMarker(
            MarkerOptions()
                .position(destinationLatLong)
                .title("destinationLatLong"))

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLong));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(10F));

        googleMap.isTrafficEnabled = true
    }

}