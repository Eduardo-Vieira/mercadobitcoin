package com.br.mercadobitcoin.ui.fragment.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.br.mercadobitcoin.R
import com.br.mercadobitcoin.database.entity.Ticker
import com.br.mercadobitcoin.ui.fragment.home.adapter.TickerListAdapter
import com.br.mercadobitcoin.ui.fragment.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private val adapter by lazy {
        TickerListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecyclerviewTicker(view)
        initObserve()
    }

    private fun configRecyclerviewTicker(view: View){
        val recyclerviewTicker = view.findViewById<RecyclerView>(R.id.recyclerview_ticker)
        recyclerviewTicker.adapter = adapter
    }

    fun adapterUpdate(ticker: List<Ticker>){
        adapter.update(ticker)
    }

    fun showError(){
        AlertDialog.Builder(requireContext())
            .setTitle(resources.getString(R.string.home_title_error))
            .setMessage(resources.getString(R.string.home_error))
            .show()
    }

    private fun initObserve(){
        homeViewModel.listViewState.observe(viewLifecycleOwner, Observer {
            homeViewModel.showTickerList(it, this)
        })
        homeViewModel.getTicker()
    }
}
