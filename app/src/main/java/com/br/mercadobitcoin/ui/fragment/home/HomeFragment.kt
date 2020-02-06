package com.br.mercadobitcoin.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.br.mercadobitcoin.R
import com.br.mercadobitcoin.ui.fragment.home.adapter.TickerListAdapter
import kotlinx.android.synthetic.main.home_fragment.*
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
        recyclerview_ticker.adapter = adapter
        initObserve()
    }

    private fun initObserve(){
        homeViewModel.getTicker().observe(viewLifecycleOwner, Observer {
            adapter.update(it?.data!!)
        })
    }
}
