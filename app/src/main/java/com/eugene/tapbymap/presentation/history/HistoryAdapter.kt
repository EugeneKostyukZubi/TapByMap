package com.eugene.tapbymap.presentation.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.eugene.tapbymap.base.adapter.BaseViewHolder
import com.eugene.tapbymap.base.adapter.ItemsAdapter
import com.eugene.tapbymap.base.adapter.SelectedItemAdapter
import com.eugene.tapbymap.model.Place
import kotlinx.android.synthetic.main.item_search_history.view.*

/**
 * Created by Eugene on 28.08.2020
 */
class HistoryAdapter(@LayoutRes private val layoutId: Int) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(),
    ItemsAdapter<Place>,
    SelectedItemAdapter<Place> {

    override var items: List<Place> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override var selectedItem: Place? = null
    override var onItemSelected: (Place) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)
            .run {
                HistoryViewHolder(this)
            }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class HistoryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        BaseViewHolder<Place> {

        override fun bind(model: Place) {
            itemView.run {
                placeTextTextView.text = model.title
                placeAddressTextView.text = model.address
                placeNameTextView.text = model.name
            }
        }
    }
}