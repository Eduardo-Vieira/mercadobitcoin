package com.br.mercadobitcoin.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.mercadobitcoin.R
import com.br.mercadobitcoin.database.entity.Ticker
import com.br.mercadobitcoin.utils.currencyFormat
import com.br.mercadobitcoin.utils.setBitTitle

class SelectListAdapter(
    private var ticker: List<Ticker> = mutableListOf()
): RecyclerView.Adapter<SelectListAdapter.SelectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_select, parent, false)
        return SelectViewHolder(v)
    }

    override fun getItemCount(): Int = ticker.size

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        holder.setItemView(ticker[position])
    }

    fun update(ticker: List<Ticker>){
        this.ticker = ticker
        notifyDataSetChanged()
    }

    inner class SelectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val mTxtId = itemView.findViewById<TextView>(R.id.select_txtId)
        private val mTxtBuy = itemView.findViewById<TextView>(R.id.select_txtBuy)
        private val mTxtTitle  = itemView.findViewById<TextView>(R.id.select_txtTitle)

        fun setItemView(item: Ticker){

            mTxtId.text   = item.id
            mTxtTitle.text = setBitTitle(item.id)
            mTxtBuy.text = String.format("Cotação %s", currencyFormat(item.buy))

        }
    }
}