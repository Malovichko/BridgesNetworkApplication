package com.androidschool.bridgesnetworkapplication.presentation.bridgesList

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidschool.bridgesnetworkapplication.R
import com.androidschool.bridgesnetworkapplication.domain.model.BridgeModel
import com.androidschool.bridgesnetworkapplication.presentation.MainActivity
import com.androidschool.bridgesnetworkapplication.presentation.bridgesList.list.BridgeListAdapter
import com.google.android.material.progressindicator.LinearProgressIndicator

class BridgeListFragment : Fragment(R.layout.fragment_list_bridges), BridgesListView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar

    private var presenter: BridgesListPresenter = BridgesListPresenter(this)

    override fun setupItemList(list: List<BridgeModel>) {
        val adapter = BridgeListAdapter(list) {
            (requireActivity() as MainActivity).openCharacterScreen(it)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val decorator = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        )
        recyclerView.addItemDecoration(decorator)
        recyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        presenter.onViewCreated()
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_character)
        toolbar = view.findViewById(R.id.toolbar)

    }
}