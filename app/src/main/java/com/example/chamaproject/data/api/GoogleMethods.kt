package com.example.chamaproject.data.api

import com.example.chamaproject.data.nearbySearch.NearbySearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleMethods {
    // Google Place API -- Nearby search
    @GET("place/nearbysearch/json")
    fun getNearbySearch(
            @Query("location") location: String,
            @Query("radius") radius: String,
            @Query("type") types: String,
            @Query("key") key: String
    ): Call<NearbySearch>
}