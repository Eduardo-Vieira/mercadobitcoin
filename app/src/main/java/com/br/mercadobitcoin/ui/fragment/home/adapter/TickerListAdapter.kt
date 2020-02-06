package com.br.mercadobitcoin.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.mercadobitcoin.R
import com.br.mercadobitcoin.database.entity.Ticker
import kotlinx.android.synthetic.main.item_ticker.view.*

class TickerListAdapter(private var ticker: List<Ticker> = mutableListOf()): RecyclerView.Adapter<TickerListAdapter.TickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_ticker, parent, false)
        return TickerViewHolder(v)
    }

    override fun getItemCount(): Int = ticker.size

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.setItemView(ticker[position])
    }

    fun update(ticker: List<Ticker>){
        this.ticker = ticker
        notifyDataSetChanged()
    }

    inner class TickerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val mTxtId = itemView.txtId
        private val mTxtHigh = itemView.txtHigh
        private val mTxtLow  = itemView.txtLow
        private val mTxtVol  = itemView.txtVol
        private val mTxtLast = itemView.txtLast
        private val mTxtBuy  = itemView.txtBuy
        private val mTxtSell = itemView.txtSell
        private val mTxtDate = itemView.txtDate

        fun setItemView(item:Ticker){
            mTxtId.text   = item.id
            mTxtHigh.text = item.high.toString()
            mTxtLow.text  = item.low.toString()
            mTxtVol.text  = item.vol.toString()
            mTxtLast.text = item.last.toString()
            mTxtBuy.text  = item.buy.toString()
            mTxtSell.text = item.sell.toString()
            mTxtDate.text = item.date.toString()
        }
    }
}