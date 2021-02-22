package com.example.chamaproject.data.repository


import com.example.chamaproject.data.api.RetrofitClient
import com.example.chamaproject.utility.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MapsRepository {

    suspend fun execute(type: String) =
        withContext(Dispatchers.IO) {
            val api =
                RetrofitClient.googleMethods( type, Constants.GOOGLE_API_KEY)
            return@withContext api!!

        }
}