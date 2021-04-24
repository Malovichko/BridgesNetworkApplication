package com.androidschool.bridgesnetworkapplication.presentation.bridgesList.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidschool.bridgesnetworkapplication.R
import com.androidschool.bridgesnetworkapplication.domain.model.BridgeModel

class BridgeListAdapter constructor(
    private val itemList: List<BridgeModel>,
    private val onClick: (bridgeId: Int) -> Unit
) : RecyclerView.Adapter<BridgesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BridgesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bridge, parent, false)
        return BridgesViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: BridgesViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

}