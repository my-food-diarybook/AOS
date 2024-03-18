package com.android.myfooddiarybookaos.data.dataMap.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat
import com.android.myfooddiarybookaos.api.KakaoApiManager
import com.android.myfooddiarybookaos.model.map.MyLocation
import com.android.myfooddiarybookaos.model.map.Place
import com.google.android.gms.location.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MapSearchRepository @Inject constructor(
    kakaoApiManager: KakaoApiManager,
    private val context: Context
) {
    private val manager = kakaoApiManager.getKakaoMapService()
    private val permissionsLocation = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )


    suspend fun getCurrentLocationData(
        myLocation: MyLocation?
    ): Flow<List<Place>> = flow {
        try {
            emit(
                manager.getCurrentLocationKeyword(
                    x = myLocation?.x,
                    y = myLocation?.y
                ).documents
            )
        } catch (_: Exception) {
        }
    }


    suspend fun getSearchKeyword(
        keyword: String,
        myLocation: MyLocation?
    ): Flow<List<Place>> = flow {
        emit(
            manager.getSearchKeyword(
                query = keyword,
                x = myLocation?.x,
                y = myLocation?.y
            ).documents
        )
    }

    fun initLocation(
        setLocation: (MyLocation) -> Unit,
    ) {
        loadLocation(
            locationData = {
                it?.let { setLocation(it) }
            }
        )

    }

    fun checkAndRequestPermissions(
        context: Context,
        launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
        result: (Boolean) -> Unit
    ) {
        if (permissionsLocation.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            result(true)
        } else {
            launcher.launch(permissionsLocation)
        }
    }

    fun getPermission() = permissionsLocation


    @SuppressLint("MissingPermission")
    private fun loadLocation(
        locationData: (MyLocation?) -> Unit
    ) {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    locationData(
                        MyLocation(
                            location.longitude.toString(),
                            location.latitude.toString()
                        )
                    )
                } else {
                    val mRequest = LocationRequest.create().apply {
                        interval = 300
                        fastestInterval = 200
                        priority = Priority.PRIORITY_HIGH_ACCURACY
                        maxWaitTime = 100
                    }
                    val mLocationCallback = object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            locationResult ?: return
                            locationResult.locations.forEach {
                                MyLocation(
                                    it.longitude.toString(),
                                    it.latitude.toString()
                                )
                                fusedLocationProviderClient.removeLocationUpdates(this)
                            }
                        }
                    }
                    fusedLocationProviderClient.requestLocationUpdates(
                        mRequest,
                        mLocationCallback,
                        null
                    )
                }
            }
            .addOnFailureListener { _ ->
                locationData(null)
            }
    }

    companion object {
        const val REQUEST_LOCATION = 1
        const val GRANT_RESULT = 100
    }
}
