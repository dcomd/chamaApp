package com.example.chamaproject.ui.maps

import android.content.Context
import com.example.chamaproject.R
import com.example.chamaproject.data.others.Spot
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.LatLng

class MapsConfig(context: Context, googleMap: GoogleMap) {

    private val mContext: Context = context
    private val mGoogleMap: GoogleMap = googleMap

    private val mTimeSquare = LatLng(40.758895, -73.985131)

    private var mSpotMarkerList = ArrayList<Marker>()

    fun setCustomMarker() {
        val blackMarkerIcon: BitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.ic_custom_marker)
        val markerOptions: MarkerOptions = MarkerOptions().position(mTimeSquare).title(mContext.getString(R.string.time_square)).snippet(mContext.getString(R.string.i_am_snippet)).icon(blackMarkerIcon)
        mGoogleMap.addMarker(markerOptions)
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mTimeSquare))
    }

    fun setMarkersAndZoom(spotList: List<Spot>) {
        val spotBitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_spot_marker)

        for (spot in spotList) {
            val name = spot.name
            val latitude = spot.lat
            val longitude = spot.lng
            val latLng = LatLng(latitude!!, longitude!!)
            val markerOptions = MarkerOptions()
            markerOptions.position(latLng).title(name).icon(spotBitmap)

            val marker = mGoogleMap.addMarker(markerOptions)
            mSpotMarkerList.add(marker)
        }

        mGoogleMap.animateCamera(MapsFactory.autoZoomLevel(mSpotMarkerList))
    }


}