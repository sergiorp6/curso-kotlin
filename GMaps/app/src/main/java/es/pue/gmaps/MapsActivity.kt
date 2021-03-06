package es.pue.gmaps

import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
                                            GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fuseLocation: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    //user location
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false

    //area estatica de la clase
    companion object {
        private const val LOCATION_REQUEST_PERMISSION_CODE = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fuseLocation = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)

                if (p0 != null){
                    lastLocation = p0.lastLocation
                    placeMarkerOnMap(LatLng(lastLocation.latitude,
                                                lastLocation.longitude))
                }

            }
        }

        createLocationRequest()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        //operaicones zoom
        if (mMap.uiSettings.isZoomControlsEnabled) {

        }
        mMap.setOnMarkerClickListener(this)

        setUpMap()
    }

    override fun onMarkerClick(p0: Marker?): Boolean {

        val barcelona = LatLng(41.3948976, 2.0787282)
        mMap.addMarker(MarkerOptions().position(barcelona).title("Marker in Barcelona"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelona,14.0f))

        return true
    }

    override fun onPause() {
        super.onPause()
        fuseLocation.removeLocationUpdates(locationCallback)
        locationUpdateState = false
    }

    override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }

    private fun placeMarkerOnMap(location:LatLng) {

        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(location,12f))

        val markerOptions =
            MarkerOptions().position(location).title("Nueva ubicación");

        mMap.addMarker(markerOptions)


    }


    private fun startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_PERMISSION_CODE)
            return
        }

        fuseLocation.requestLocationUpdates(locationRequest,locationCallback,null)
    }

    private fun createLocationRequest() {

        locationRequest = LocationRequest()

        with(locationRequest){
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        //exito
        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }

        //failure
        //TODO
    }


    //permisos
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                            LOCATION_REQUEST_PERMISSION_CODE)
                    return
        }

        mMap.isMyLocationEnabled = true

        fuseLocation.lastLocation.addOnSuccessListener(this) { location ->

            if (location != null) {
                lastLocation = location
                val userLocationLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(userLocationLatLng,12f))

            }
        }


    }
}
