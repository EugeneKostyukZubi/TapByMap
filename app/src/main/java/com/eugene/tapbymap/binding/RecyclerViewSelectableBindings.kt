package com.eugene.tapbymap.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.eugene.tapbymap.base.adapter.ActionsItemAdapter
import com.eugene.tapbymap.base.adapter.ItemsAdapter
import com.eugene.tapbymap.base.adapter.SelectedItemAdapter

/**
 * Created by Eugene on 2019-11-16
 */
object RecyclerViewSelectableBindings {
    @JvmStatic
    @BindingAdapter(value = ["items"])
    fun <T> setItems(recyclerView: RecyclerView, items: List<T>) {
        @Suppress("UNCHECKED_CAST")
        if ((recyclerView.adapter as ItemsAdapter<T>).items != items) {
            (recyclerView.adapter as ItemsAdapter<T>).items = items
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["selectedItem"])
    fun <T> setSelection(recyclerView: RecyclerView, selectedItem: T) {
        @Suppress("UNCHECKED_CAST")
        if ((recyclerView.adapter as SelectedItemAdapter<T>).selectedItem != selectedItem) {
            (recyclerView.adapter as SelectedItemAdapter<T>).selectedItem = selectedItem
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "selectedItem")
    fun <T> getRecyclerViewListener(view: RecyclerView): T {
        @Suppress("UNCHECKED_CAST")
        return (view.adapter as SelectedItemAdapter<T>).selectedItem!!
    }

    @JvmStatic
    @BindingAdapter(value = ["selectedItemAttrChanged"])
    fun <T> setRecyclerViewListener(view: RecyclerView, listener: InverseBindingListener) {
        @Suppress("UNCHECKED_CAST")
        (view.adapter as SelectedItemAdapter<T>).onItemSelected = {
            listener.onChange()
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["actionCalledItem"])
    fun <T> setActionSelection(recyclerView: RecyclerView, actionCalledItem: T) {
        @Suppress("UNCHECKED_CAST")
        if ((recyclerView.adapter as ActionsItemAdapter<T>).actionCalled != actionCalledItem) {
            (recyclerView.adapter as ActionsItemAdapter<T>).actionCalled = actionCalledItem
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "actionCalledItem")
    fun <T> getActionRecyclerViewListener(view: RecyclerView): T {
        @Suppress("UNCHECKED_CAST")
        return (view.adapter as ActionsItemAdapter<T>).actionCalled!!
    }

    @JvmStatic
    @BindingAdapter(value = ["actionCalledItemAttrChanged"])
    fun <T> setActionRecyclerViewListener(view: RecyclerView, listener: InverseBindingListener) {
        @Suppress("UNCHECKED_CAST")
        (view.adapter as ActionsItemAdapter<T>).onItemActionSelected = {
            listener.onChange()
        }
    }
}