package com.androidschool.bridgesnetworkapplication.presentation.mapScreen

// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.androidschool.bridgesnetworkapplication.R
import com.androidschool.bridgesnetworkapplication.domain.model.BridgeModel
import com.androidschool.bridgesnetworkapplication.presentation.processingTime.BridgeStatus
import com.androidschool.bridgesnetworkapplication.presentation.processingTime.ProcessingTime
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
class MapsActivity :
    AppCompatActivity(),
    OnMapReadyCallback, MapView {

    private var presenter: MapPresenter = MapPresenter(this)

    /** map to store place names and locations */
    private var places = mutableMapOf(
        "CENTER" to LatLng(59.56, 30.18),
    )

    private val bridgesItemName = listOf("FIRST_BRIDGE", "SECOND_BRIDGE", "TH_BRIDGE")

    private var icons: MutableMap<String, BitmapDescriptor> = mutableMapOf()

    val SYDNEY = LatLng(59.56, 30.18)
    val ZOOM_LEVEL = 13f

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onViewCreated()
        setContentView(R.layout.activity_maps)

        val mapFragment: SupportMapFragment? =
            supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)


    }

    override fun setupItemList(list: List<BridgeModel>) {
        Log.i("info", "setupItemList")
        val listIterator = list.iterator()
        val namesListIterator = bridgesItemName.iterator()
        while (listIterator.hasNext() && namesListIterator.hasNext()) {
            val bridge = listIterator.next()
            val bridgeName = namesListIterator.next()
            val location = LatLng(bridge.lat.toDouble(), bridge.lng.toDouble())
            places[bridgeName] = location
            icons[bridgeName] = setTimeDivorces(bridge)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap ?: return
//        with(googleMap) {
        with(map) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, ZOOM_LEVEL))
            addMarker(
                MarkerOptions()
                    .position(SYDNEY)
            )
        }
        addMarkersToMap()
    }

    private fun addMarkersToMap() {
        Log.i("info", "addMarkersToMap")
        val placeDetailsMap = mutableMapOf(
            // Use a vector drawable resource as a marker icon.
            "CENTER" to PlaceDetails(
                position = places.getValue("CENTER"),
                title = "Alice Springs",
                icon = vectorToBitmap(
                    R.drawable.ic_brige_unknown
                )
            ),
            "FIRST_BRIDGE" to PlaceDetails(
                position = places.getValue("FIRST_BRIDGE"),
                title = "Alice",
                icon = icons?.get("FIRST_BRIDGE")!!
            ),

            "SECOND_BRIDGE" to PlaceDetails(
                position = places.getValue("SECOND_BRIDGE"),
                title = "Alice",
                icon = icons?.get("SECOND_BRIDGE")!!
            ),

            "TH_BRIDGE" to PlaceDetails(
                position = places.getValue("TH_BRIDGE"),
                title = "Alice",
                icon = icons?.get("TH_BRIDGE")!!
            )
        )

        placeDetailsMap.keys.map {
            with(placeDetailsMap.getValue(it)) {
                map.addMarker(
                    MarkerOptions()
                        .position(position)
                        .title(title)
                        .snippet(snippet)
                        .icon(icon)
                        .infoWindowAnchor(infoWindowAnchorX, infoWindowAnchorY)
                        .draggable(draggable)
                        .zIndex(zIndex)
                )
            }
        }
    }

    private fun vectorToBitmap(@DrawableRes id: Int): BitmapDescriptor {
        val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources, id, null)
        if (vectorDrawable == null) {
            Log.e("TAG", "Resource not found")
            return BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun setTimeDivorces(item: BridgeModel): BitmapDescriptor {
        val setIcons = ProcessingTime()
        return when (setIcons.getBridgeIndicator(item.start, item.end)) {
            BridgeStatus.OPEN -> vectorToBitmap(
                R.drawable.ic_brige_normal
            )
            BridgeStatus.CLOSE -> vectorToBitmap(
                R.drawable.ic_brige_late
            )
            BridgeStatus.SOON_CLOSE -> vectorToBitmap(R.drawable.ic_brige_soon)
            BridgeStatus.ERR -> vectorToBitmap(R.drawable.ic_brige_unknown)
        }
    }


}

class PlaceDetails(
    val position: LatLng,
    val title: String = "Marker",
    val snippet: String? = null,
    val icon: BitmapDescriptor = BitmapDescriptorFactory.defaultMarker(),
    val infoWindowAnchorX: Float = 0.5F,
    val infoWindowAnchorY: Float = 0F,
    val draggable: Boolean = false,
    val zIndex: Float = 0F
)





