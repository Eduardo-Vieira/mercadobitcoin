package com.br.mercadobitcoin.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.mercadobitcoin.R
import com.br.mercadobitcoin.database.entity.Ticker
import com.br.mercadobitcoin.utils.currencyFormat
import com.br.mercadobitcoin.utils.dateFormat

class TickerListAdapter(
    private var ticker: List<Ticker> = mutableListOf()
): RecyclerView.Adapter<TickerListAdapter.TickerViewHolder>() {

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

        private val mTxtId = itemView.findViewById<TextView>(R.id.txtId)
        private val mTxtHigh = itemView.findViewById<TextView>(R.id.txtHigh)
        private val mTxtLow  = itemView.findViewById<TextView>(R.id.txtLow)
        private val mTxtVol  = itemView.findViewById<TextView>(R.id.txtVol)
        private val mTxtLast = itemView.findViewById<TextView>(R.id.txtLast)
        private val mTxtBuy  = itemView.findViewById<TextView>(R.id.txtBuy)
        private val mTxtSell = itemView.findViewById<TextView>(R.id.txtSell)
        private val mTxtDate = itemView.findViewById<TextView>(R.id.txtDate)

        fun setItemView(item:Ticker){
            mTxtId.text   = item.id
            mTxtHigh.text = String.format("Maior preço: %s", currencyFormat(item.high))
            mTxtLow.text  = String.format("Menor preço: %s", currencyFormat(item.low))
            mTxtVol.text  = String.format("Volume 24hs: %s", currencyFormat(item.vol))
            mTxtLast.text = String.format("Última negociação: %s", currencyFormat(item.last))
            mTxtBuy.text  = String.format("Maior preço de oferta: %s", currencyFormat(item.buy))
            mTxtSell.text = String.format("Menor preço de oferta: %s", currencyFormat(item.sell))
            mTxtDate.text = String.format("Data: %s",dateFormat(item.date))
        }
    }
}