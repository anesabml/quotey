package com.anesabml.quotey.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anesabml.quotey.domain.model.Quote
import com.anesabml.quotey.databinding.ItemQuoteBinding


class MyItemQuoteRecyclerViewAdapter(private var values: List<Quote>) : RecyclerView.Adapter<MyItemQuoteRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemQuoteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.quote

    }

    override fun getItemCount(): Int = values.size

    fun changeContent(list: List<Quote>) {
        values = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemQuoteBinding: ItemQuoteBinding) : RecyclerView.ViewHolder(itemQuoteBinding.root) {
        val contentView: TextView = itemQuoteBinding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}
