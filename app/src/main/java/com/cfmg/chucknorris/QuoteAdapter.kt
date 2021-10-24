package com.cfmg.chucknorris

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(private val quoteList: ArrayList<QuoteData>): RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteAdapter.QuoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_quote_single, parent, false)
        return QuoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuoteAdapter.QuoteViewHolder, position: Int) {

        val quote : QuoteData = quoteList[position]
        holder.quoteA.text = quote.quote.toString()


    }

    override fun getItemCount(): Int {
        return quoteList.size
    }


    public class QuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val quoteA: TextView = itemView.findViewById(R.id.tv_quote)
    }
}