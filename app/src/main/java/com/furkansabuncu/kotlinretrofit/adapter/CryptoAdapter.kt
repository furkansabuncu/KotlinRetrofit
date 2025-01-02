package com.furkansabuncu.kotlinretrofit.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkansabuncu.kotlinretrofit.R
import com.furkansabuncu.kotlinretrofit.adapter.CryptoAdapter.CryptoHolder
import com.furkansabuncu.kotlinretrofit.model.CryptoModel

class CryptoAdapter(private val cryptoList : ArrayList<CryptoModel>, private val listener: Listener) : RecyclerView.Adapter<CryptoHolder>() {

    private val colors : Array<String> = arrayOf("#325EB6","#E2C622","#ADADDE","#EDAEAE")

    interface Listener{
        fun onItemClick(cryptoModel : CryptoModel)


    }
    class CryptoHolder(view : View) : RecyclerView.ViewHolder(view){
        private val cryptoNameText: TextView = itemView.findViewById(R.id.textName)
        private val cryptoPriceText: TextView = itemView.findViewById(R.id.textPrice)

        fun bind(cryptoModel: CryptoModel, colors: Array<String>, position: Int, listener: Listener){
            itemView.setOnClickListener{
                listener.onItemClick(cryptoModel)
            }
            cryptoNameText.text = cryptoModel.currency
            cryptoPriceText.text=cryptoModel.price
            itemView.setBackgroundColor(Color.parseColor(colors[position % 4 ]))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return CryptoHolder(view)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(cryptoList[position], colors, position , listener)
    }
}