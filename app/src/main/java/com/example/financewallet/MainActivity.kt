package com.example.financewallet

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.location.LocationManager
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.provider.MediaStore
import android.widget.Toast
import com.example.financewallet.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val PERMISSION_REQUEST_CODE = 100
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val navView: BottomNavigationView = _binding.navigationViewBtm

        val navController = findNavController(R.id.fragment_container_view)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_assets,
                R.id.navigation_portfolio,
                R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (checkAndRequestPermissions()) {
            startLocationUpdates()
            startTakingPhotos()
        }
    }

    private fun checkAndRequestPermissions(): Boolean {
        val locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        val listPermissionsNeeded = mutableListOf<String>()

        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), PERMISSION_REQUEST_CODE)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                val perms = HashMap<String, Int>()
                perms[Manifest.permission.ACCESS_FINE_LOCATION] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.CAMERA] = PackageManager.PERMISSION_GRANTED

                grantResults.forEachIndexed { index, result ->
                    perms[permissions[index]] = result
                }

                val allPermissionsGranted = perms[Manifest.permission.ACCESS_FINE_LOCATION] == PackageManager.PERMISSION_GRANTED &&
                        perms[Manifest.permission.CAMERA] == PackageManager.PERMISSION_GRANTED

                if (allPermissionsGranted) {
                    startLocationUpdates()
                    startTakingPhotos()
                } else {
                    Toast.makeText(this, "Needs all permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun startLocationUpdates() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                Toast.makeText(applicationContext, "Location: ${location.latitude}, ${location.longitude}", Toast.LENGTH_LONG).show()
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 5f, locationListener)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun startTakingPhotos() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                takePhoto()
                handler.postDelayed(this, 5000)
            }
        }
        handler.post(runnable)
    }

    private fun takePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(packageManager) != null) {
            startActivity(cameraIntent)
        }
    }
}
