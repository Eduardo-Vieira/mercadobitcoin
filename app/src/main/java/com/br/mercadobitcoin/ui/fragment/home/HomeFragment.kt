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
import com.br.mercadobitcoin.ui.fragment.home.adapter.SelectListAdapter
import com.br.mercadobitcoin.ui.fragment.home.adapter.TickerListAdapter
import com.br.mercadobitcoin.ui.fragment.home.viewmodel.HomeViewModel
import com.br.mercadobitcoin.utils.currencyFormat
import com.br.mercadobitcoin.utils.setBitTitle
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private val adapter by lazy {
        TickerListAdapter()
    }
    private var adapterSelect: SelectListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecyclerviewTicker(view)
        configRecyclerviewSelect(view)
        initObserve()
    }

    private fun configRecyclerviewSelect(view: View){
        adapterSelect = SelectListAdapter( mutableListOf(), itemClick = {
            clickItemSelect(it)
        })
        val recyclerviewSelect = view.findViewById<RecyclerView>(R.id.recyclerView_select)
        recyclerviewSelect.adapter = adapterSelect
    }

    private fun clickItemSelect(item: Ticker){
        AlertDialog.Builder(requireContext())
            .setTitle(setBitTitle(item.id))
            .setMessage(String.format("Cotação %s", currencyFormat(item.buy)))
            .show()
    }

    private fun configRecyclerviewTicker(view: View){
        val recyclerviewTicker = view.findViewById<RecyclerView>(R.id.recyclerview_ticker)
        recyclerviewTicker.adapter = adapter
    }

    fun adapterUpdate(ticker: List<Ticker>){
        adapter.update(ticker)
        adapterSelect?.update(ticker)
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
