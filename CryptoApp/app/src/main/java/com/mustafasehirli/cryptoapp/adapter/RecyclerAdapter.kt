package com.mustafasehirli.cryptoapp.adapter


import android.graphics.Color
import android.location.GnssAntennaInfo
import android.location.GpsStatus
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafasehirli.cryptoapp.R
import com.mustafasehirli.cryptoapp.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*
import okhttp3.internal.http2.Http2Connection

class RecyclerAdapter(private val cryptoList:ArrayList<CryptoModel>,private val listener:Listener):RecyclerView.Adapter<RecyclerAdapter.RowHolder>(){
    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private val colors :Array<String> = arrayOf("#0DB3E3","#FFBB00","#FF4343","#1A3365","#3A4A55","#FD9519","#5696FA","#054370")

    class RowHolder(view: View):RecyclerView.ViewHolder(view) {

        fun bind(cryptoModel: CryptoModel,colors:Array<String>,position: Int,listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.crypto_name.text=cryptoModel.currency
            itemView.crypto_price.text=cryptoModel.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position,listener)
    }

    override fun getItemCount(): Int {
      return cryptoList.size
    }


}