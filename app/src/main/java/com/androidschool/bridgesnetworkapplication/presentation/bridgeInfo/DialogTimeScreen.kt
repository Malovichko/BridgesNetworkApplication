package com.androidschool.bridgesnetworkapplication.presentation.bridgeInfo

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView
import com.androidschool.bridgesnetworkapplication.R
import kotlinx.android.synthetic.main.fragment_bridge_detail_info.*
import kotlinx.android.synthetic.main.scroller_dialog_time.*
import org.w3c.dom.Text


object DialogTimeScreen {
    const val IDD_REMIND_ME = 1


    fun getDialog(fragment: BridgeFragment, ID: Int, titleDialogScreen: String): AlertDialog? {

        val builder: AlertDialog.Builder = AlertDialog.Builder(fragment.requireContext())
        return when (ID) {
            IDD_REMIND_ME -> {
                val dialogLayout = LayoutInflater.from(fragment.requireContext())
                    .inflate(R.layout.scroller_dialog_time, null)
                val numberPicker: NumberPicker = dialogLayout.findViewById(R.id.number_picker)
                val title: TextView = dialogLayout.findViewById(R.id.dialog_title)
                val subtitle: TextView = dialogLayout.findViewById(R.id.dialog_subtitle)
                val timeForPicker = arrayOf("15 мин", "30 мин", "45 мин")

                numberPicker.maxValue = timeForPicker.size - 1
                numberPicker.minValue = 0
                numberPicker.wrapSelectorWheel = false
                numberPicker.displayedValues = timeForPicker
                title.text = titleDialogScreen
                subtitle.setText(R.string.dialog_message)

                builder.setView(
                    dialogLayout
                )
                var chosenTime: String? = "За 15 минут"
                numberPicker.setOnValueChangedListener { _, _, newVal ->
                    chosenTime = "За ${timeForPicker[newVal]}ут"

                }
                builder.setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, which ->
                        fragment.button_remind_me.title = chosenTime
                        dialog.dismiss()
                    })
                builder.setNegativeButton(
                    R.string.cancel,
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    }
                )

                builder.create()
            }
            else -> null
        }
    }
}