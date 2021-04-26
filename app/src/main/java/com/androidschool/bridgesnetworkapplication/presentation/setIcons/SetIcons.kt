package com.androidschool.bridgesnetworkapplication.presentation.setIcons

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

class SetIcons {
    private val timeZone = TimeZone.getTimeZone("Europe/Moscow")
    private var timeZone1 = TimeZone.getTimeZone("Europe/Moscow")
    private val timeZoneOffset = ((timeZone1.rawOffset - timeZone.rawOffset) / 1000).toLong()
    private lateinit var startStr: String
    private lateinit var endStr: String

    fun setTimeDivorces(
        timeStart: String,
        timeEnd: String
    ): BridgeStatus {
        startStr = if (timeStart.length == 4) "0$timeStart" else timeStart
        endStr = if (timeEnd.length == 4) "0$timeEnd" else timeEnd
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timeZone1 = TimeZone.getTimeZone(ZoneId.systemDefault())
            var start =
                if (timeStart.length == 4) LocalTime.parse("0$timeStart") else LocalTime.parse(
                    timeStart
                )
            var end = if (timeEnd.length == 4) LocalTime.parse("0$timeEnd") else LocalTime.parse(
                timeEnd
            )
            start = start.plusSeconds(timeZoneOffset)
            end = end.plusSeconds(timeZoneOffset)
            startStr = start.toString()
            endStr = end.toString()
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("HH:mm")
            val getCurrentDateTime = sdf.format(calendar.time)
            val time = LocalTime.parse(getCurrentDateTime)

            return if (time.isBefore(start) || time.isAfter(end)) {
                if (time.isBefore(start)) {
                    val moreHour = (start.toSecondOfDay() - time.toSecondOfDay()) / 3600 > 0
                    if (moreHour) BridgeStatus.OPEN
                    else BridgeStatus.SOON_CLOSE
                } else BridgeStatus.OPEN
            } else BridgeStatus.CLOSE
        }
        return BridgeStatus.ERR
    }

    fun getStartTime() = startStr

    fun getEndTime() = endStr


}