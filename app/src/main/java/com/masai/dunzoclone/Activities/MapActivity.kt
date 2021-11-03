package com.masai.dunzoclone.Activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masai.dunzoclone.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_map.*
import java.util.*


class MapActivity : AppCompatActivity(), OnMapReadyCallback{
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Double = 28.7041
    private var long: Double = 77.1025
    private lateinit var geocoder: Geocoder
    private lateinit var address: List<Address>
    private lateinit var fullAddress: String
    //Firebase
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    private val usersRef = db.collection("users");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        auth = Firebase.auth
        geocoder = Geocoder(this, Locale.getDefault())
        checkLocationPermission()
        locationLatLang()

        btnConfirmLocation.setOnClickListener {
            setAddress()
            Toast.makeText(this, "Your address is saved", Toast.LENGTH_LONG).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> locationLatLang()
            }
        }
    }
    private fun checkLocationPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        val add: String = address[0].getAddressLine(0)
        val city: String = address[0].locality
        val state: String = address[0].adminArea
        val country: String =address[0].countryName
        val postalCode: String = address[0].postalCode
        fullAddress = "$add, $city, $state, $country, $postalCode"

        val latLong = LatLng(lat, long)
        googleMap.addMarker(
            MarkerOptions()
                .position(latLong)
                .title(fullAddress))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLong));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(10F));

        googleMap.isTrafficEnabled = true
    }

    private fun setAddress() {
        val updatedAddress = hashMapOf("address" to fullAddress)
        auth.currentUser?.uid?.let {
            usersRef.document(it).set(updatedAddress, SetOptions.merge())
        }
    }

}