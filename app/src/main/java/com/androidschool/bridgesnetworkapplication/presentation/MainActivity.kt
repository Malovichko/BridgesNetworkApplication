package com.androidschool.bridgesnetworkapplication.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.androidschool.bridgesnetworkapplication.R
import com.androidschool.bridgesnetworkapplication.presentation.bridgeInfo.BridgeFragment
import com.androidschool.bridgesnetworkapplication.presentation.bridgesList.BridgeListFragment
import com.androidschool.bridgesnetworkapplication.presentation.mapScreen.MapsActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(){

    private lateinit var buttonMap: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initListener()

        openListScreen()
    }

    private fun initView(){
        buttonMap = findViewById(R.id.button_map)
    }

    private fun initListener(){
        buttonMap.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

    }

    private fun openListScreen() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            BridgeListFragment()
        ).commit()
    }


    fun openCharacterScreen(bridgeId: Int) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            BridgeFragment.newInstance(bridgeId)
        )
            .addToBackStack(null)
            .commit()
    }
}