package com.example.chamaproject.data.api


import com.example.chamaproject.data.nearbySearch.NearbySearch
import com.example.chamaproject.data.others.Spot
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.OkHttpClient
import okhttp3.Request


object RetrofitClient {

    suspend fun googleMethods(type: String, key: String): ArrayList<Spot> {
        val spotList = ArrayList<Spot>()
        val retorno = CoroutineScope(Dispatchers.IO).async {
            val request = Request.Builder()
                .url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.86879,151.194217&radius=1500&type=${type}&key=${key}")
                .build()

            val response = OkHttpClient().newCall(request).execute().body!!.string()
            val gson = Gson()
            val nearb = gson.fromJson(response, NearbySearch::class.java)

            nearb?.let {
                for (resultItem in nearb!!.results!!) {
                    val spot = Spot(
                        resultItem.name,
                        resultItem.geometry.location?.lat,
                        resultItem.geometry.location?.lng
                    )
                    spotList.add(spot)
                }
            }
        }
        retorno.await()

        return spotList

    }
}


