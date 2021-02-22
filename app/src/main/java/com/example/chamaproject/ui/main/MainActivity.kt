package com.example.chamaproject.ui.main


import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.util.rangeTo
import com.example.chamaproject.R
import com.example.chamaproject.data.nearbySearch.NearbySearch
import com.example.chamaproject.ui.maps.MapsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private var navigation : BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation = findViewById(R.id.bottom_navigation)

        bottom_navigation?.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_cofee ->{
                    callIntent("cafes")
                }
                R.id.navigation_bar ->{
                    callIntent("bars")

                }R.id.navigation_rest ->{
                callIntent("restaurant")
               }
            }
            false
        }
    }

    private  fun callIntent(type:String){
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("type",type )
        startActivity(intent)

    }
}