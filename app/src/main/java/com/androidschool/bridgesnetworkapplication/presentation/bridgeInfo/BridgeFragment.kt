package com.androidschool.bridgesnetworkapplication.presentation.bridgeInfo

import android.icu.number.NumberFormatter.with
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.androidschool.bridgesnetworkapplication.R
import com.androidschool.bridgesnetworkapplication.domain.model.TimeModel
import com.androidschool.bridgesnetworkapplication.presentation.customButton.IconButton
import com.androidschool.bridgesnetworkapplication.presentation.setIcons.BridgeStatus
import com.androidschool.bridgesnetworkapplication.presentation.setIcons.SetIcons
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

class BridgeFragment : Fragment(R.layout.fragment_bridge_detail_info), BridgeView {


    private lateinit var nameBridge: TextView
    private lateinit var bridgeInfo: TextView
    private lateinit var imageBridge: ImageView
    private lateinit var textViewTime: TextView
    private lateinit var bridgeIndicator: ImageView
    private lateinit var buttonRemindMe: IconButton

    private val presenter = BridgePresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListener()

    }

    private fun initViews(view: View) {
        nameBridge = view.findViewById(R.id.text_bridge_title)
        bridgeInfo = view.findViewById(R.id.bridge_info)
        textViewTime = view.findViewById(R.id.text_bridge_divorces_time)
        bridgeIndicator = view.findViewById(R.id.bridge_indicator)
        imageBridge = view.findViewById(R.id.image_bridge)
        buttonRemindMe = view.findViewById(R.id.button_remind_me)
        bridgeIndicator.setImageResource(R.drawable.ic_brige_unknown)

        presenter.onViewCreated(arguments?.getInt(BRIDGE_ID_KEY))
    }

    private fun initListener() {
        buttonRemindMe.setOnClickListener {
            val dialog = DialogTimeScreen.getDialog(
                this,
                DialogTimeScreen.IDD_REMIND_ME,
                nameBridge.text.toString()
            )
            dialog?.show()
        }
    }

    companion object {
        private const val BRIDGE_ID_KEY = "BridgeFragment.BRIDGE_ID_KEY"

        fun newInstance(id: Int): BridgeFragment {
            val args = Bundle()
            args.putInt(BRIDGE_ID_KEY, id)

            val fragment = BridgeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun fillData(
        id: Int,
        name: String,
        description: String,
        photo_open_url: String,
        photo_close_url: String
    ) {
        nameBridge.text = name
        bridgeInfo.text = description
        Picasso.get().load(photo_open_url).into(imageBridge)
    }

    override fun setupTimeList(list: List<TimeModel>, id: Int) {
        val setIcons = SetIcons()
        when (setIcons.setTimeDivorces(list.get(id - 1).start, list.get(id - 1).end)) {
            BridgeStatus.OPEN -> bridgeIndicator.setImageResource(R.drawable.ic_brige_normal)
            BridgeStatus.CLOSE -> bridgeIndicator.setImageResource(R.drawable.ic_brige_late)
            BridgeStatus.SOON_CLOSE -> bridgeIndicator.setImageResource(R.drawable.ic_brige_soon)
            BridgeStatus.ERR -> return
        }
        textViewTime.text = setIcons.getStartTime() + " - " + setIcons.getEndTime()
    }


}