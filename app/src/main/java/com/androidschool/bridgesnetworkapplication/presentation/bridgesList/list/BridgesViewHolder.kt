package com.androidschool.bridgesnetworkapplication.presentation.bridgesList.list

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidschool.bridgesnetworkapplication.R
import com.androidschool.bridgesnetworkapplication.domain.model.BridgeModel
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

        val timeZone = TimeZone.getTimeZone("Europe/Moscow")
        var timeZone1 = TimeZone.getTimeZone("Europe/Moscow")
        val timeZoneOffset = ((timeZone1.rawOffset - timeZone.rawOffset) / 1000).toLong()
        var startStr = if (item.start.length == 4) "0" + item.start else item.start
        var endStr = if (item.end.length == 4) "0" + item.end else item.end
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timeZone1 = TimeZone.getTimeZone(ZoneId.systemDefault())
            var start =
                if (item.start.length == 4) LocalTime.parse("0" + item.start) else LocalTime.parse(
                    item.start
                )
            var end =
                if (item.end.length == 4) LocalTime.parse("0" + item.end) else LocalTime.parse(
                    item.end
                )
            start = start.plusSeconds(timeZoneOffset)
            end = end.plusSeconds(timeZoneOffset)
            startStr = start.toString()
            endStr = end.toString()

            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("HH:mm")
            val getCurrentDateTime = sdf.format(calendar.time)
            val time = LocalTime.parse(getCurrentDateTime)

            if (time.isBefore(start) || time.isAfter(end)) {
                if (time.isBefore(start)) {
                    val moreHour = (start.toSecondOfDay() - time.toSecondOfDay()) / 3600 > 0
                    if (moreHour) bridgeIndicator.setImageResource(R.drawable.ic_brige_normal)
                    else bridgeIndicator.setImageResource(R.drawable.ic_brige_soon)
                } else bridgeIndicator.setImageResource(R.drawable.ic_brige_normal)
            } else bridgeIndicator.setImageResource(R.drawable.ic_brige_late)
        }
        val time = "$startStr - $endStr";

        textViewTime.text = time
    }
}