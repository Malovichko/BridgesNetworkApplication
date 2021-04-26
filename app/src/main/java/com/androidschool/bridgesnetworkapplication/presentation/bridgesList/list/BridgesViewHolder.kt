package com.androidschool.bridgesnetworkapplication.presentation.bridgesList.list

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidschool.bridgesnetworkapplication.R
import com.androidschool.bridgesnetworkapplication.domain.model.BridgeModel
import com.androidschool.bridgesnetworkapplication.presentation.setIcons.BridgeStatus
import com.androidschool.bridgesnetworkapplication.presentation.setIcons.SetIcons
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

class BridgesViewHolder(
    rootView: View,
    private val onClick: (bridgeId: Int) -> Unit
) : RecyclerView.ViewHolder(rootView) {
    private lateinit var textViewTitle: TextView
    private lateinit var textViewTime: TextView
    private lateinit var bridgeIndicator: ImageView
    private lateinit var bell: ImageView

    fun bind(item: BridgeModel) {
        textViewTitle = itemView.findViewById(R.id.text_item_title)
        textViewTime = itemView.findViewById(R.id.text_item_time)
        bridgeIndicator = itemView.findViewById(R.id.item_bridge_indicator)
        bell = itemView.findViewById(R.id.item_icon_bell)

        textViewTitle.text = item.name
        bridgeIndicator.setImageResource(R.drawable.ic_brige_unknown)

        setTimeDivorces(item)

        itemView.setOnClickListener {
            onClick(
                item.id
            )
        }
    }

    private fun setTimeDivorces(item: BridgeModel) {
        val setIcons = SetIcons()
        when (setIcons.setTimeDivorces(item.start, item.end)) {
            BridgeStatus.OPEN -> bridgeIndicator.setImageResource(R.drawable.ic_brige_normal)
            BridgeStatus.CLOSE -> bridgeIndicator.setImageResource(R.drawable.ic_brige_late)
            BridgeStatus.SOON_CLOSE -> bridgeIndicator.setImageResource(R.drawable.ic_brige_soon)
            BridgeStatus.ERR -> return
        }
        textViewTime.text = setIcons.getStartTime() + " - " + setIcons.getEndTime()
    }
}