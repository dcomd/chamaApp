package com.example.chamaproject.ui.maps


import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker



object MapsFactory {

    fun autoZoomLevel(markerList: List<Marker>): CameraUpdate {
        if (markerList.size == 1) {
            val latitude = markerList[0].position.latitude
            val longitude = markerList[0].position.longitude
            val latLng = LatLng(latitude, longitude)

            return CameraUpdateFactory.newLatLngZoom(latLng, 13f)
        } else {
            val builder = LatLngBounds.Builder()
            for (marker in markerList) {
                builder.include(marker.position)
            }
            val bounds = builder.build()

            val padding = 200 // offset from edges of the map in pixels

            return CameraUpdateFactory.newLatLngBounds(bounds, padding)
        }
    }
}