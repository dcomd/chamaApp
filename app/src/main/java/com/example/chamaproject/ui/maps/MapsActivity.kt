package com.example.chamaproject.ui.maps

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.chamaproject.R
import com.example.chamaproject.ui.erro.ErroActivity
import com.example.chamaproject.ui.viewmodel.MapsViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel



class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val viewModel: MapsViewModel by viewModel()

    private lateinit var mGoogleMap: GoogleMap
    private var type: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        type = intent.getStringExtra("type").toString()
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        var mMapsConfig = MapsConfig(this, mGoogleMap)
        mMapsConfig.setCustomMarker()
        CoroutineScope(Dispatchers.Main).launch {
            progressLayout.visibility = View.VISIBLE
            val list = viewModel.setResultSearch( type)
            if (list.isNotEmpty()) {
                mMapsConfig.setMarkersAndZoom(list)
            } else {
                startActivity(Intent(this@MapsActivity, ErroActivity::class.java))
            }
            progressLayout.visibility = View.GONE
        }
    }
}
