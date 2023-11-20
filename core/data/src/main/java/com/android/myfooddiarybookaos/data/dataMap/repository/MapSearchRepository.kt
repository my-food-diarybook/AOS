package com.android.myfooddiarybookaos.data.dataMap.repository

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.android.myfooddiarybookaos.api.KakaoApiManager
import com.android.myfooddiarybookaos.model.map.MyLocation
import com.android.myfooddiarybookaos.model.map.ResultSearchKeyword
import com.google.android.gms.location.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MapSearchRepository @Inject constructor(
    kakaoApiManager: KakaoApiManager,
    private val context: Context
) {
    private val manager = kakaoApiManager.getKakaoService()
    private val permissionsLocation = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )


    fun getCurrentLocationData(
        myLocation: MyLocation?,
        result: (ResultSearchKeyword?) -> Unit
    ) {
        manager.getCurrentLocationKeyword(
            x = myLocation?.x,
            y = myLocation?.y
        ).enqueue(object : Callback<ResultSearchKeyword> {
            override fun onResponse(
                call: Call<ResultSearchKeyword>,
                response: Response<ResultSearchKeyword>
            ) {
                result(response.body())
            }

            override fun onFailure(call: Call<ResultSearchKeyword>, t: Throwable) {
                result(null)
            }

        })

    }

    fun getSearchKeyword(
        keyword: String,
        myLocation: MyLocation?,
        result: (ResultSearchKeyword?) -> Unit
    ) {
        manager.getSearchKeyword(
            query = keyword,
            x = myLocation?.x,
            y = myLocation?.y
        ).enqueue(object : Callback<ResultSearchKeyword> {
            override fun onResponse(
                call: Call<ResultSearchKeyword>,
                response: Response<ResultSearchKeyword>
            ) {
                result(response.body())
            }

            override fun onFailure(call: Call<ResultSearchKeyword>, t: Throwable) {
                result(null)
            }

        })
    }

    fun initLocation(
        activity: Activity,
        setLocation: (MyLocation) -> Unit,
    ) {
        if (notCheckPermission()) {
            requestLocation(activity)
        }
        loadLocation(
            locationData = {
                it?.let { setLocation(it) }
            }
        )

    }

    private fun notCheckPermission(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            context,
            permissionsLocation[0]
        ) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(
            context,
            permissionsLocation[1]
        ) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    context,
                    permissionsLocation[2]
                ) != PackageManager.PERMISSION_GRANTED
                )
    }

    private fun requestLocation(
        activity: Activity
    ) {
        ActivityCompat.requestPermissions(
            activity,
            permissionsLocation,
            REQUEST_LOCATION
        )

    }


    @SuppressLint("MissingPermission")
    private fun loadLocation(
        locationData: (MyLocation?) -> Unit
    ) {
        Log.d("checkMyresult", "loadLocation")
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                Log.d("checkMyresult", "addOnSuccessListener" + location.toString())
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
                    val mLocationCallback = object  : LocationCallback(){
                        override fun onLocationResult(locationResult: LocationResult) {
                            locationResult ?: return
                            locationResult.locations.forEach{
                                MyLocation(
                                    it.longitude.toString(),
                                    it.latitude.toString()
                                )
                                fusedLocationProviderClient.removeLocationUpdates(this)
                            }
                        }
                    }
                    fusedLocationProviderClient.requestLocationUpdates(mRequest,mLocationCallback,null)
                }
            }
            .addOnFailureListener { _ ->
                Log.d("checkMyresult", "addOnFailureListener")
                locationData(null)
            }
    }

    companion object {
        const val REQUEST_LOCATION = 1
    }
}