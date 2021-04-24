package com.androidschool.bridgesnetworkapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidschool.bridgesnetworkapplication.R
import com.androidschool.bridgesnetworkapplication.presentation.bridgeInfo.BridgeFragment
import com.androidschool.bridgesnetworkapplication.presentation.bridgesList.BridgeListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openListScreen()
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