package com.eugene.tapbymap.base.adapter

interface SelectedItemAdapter<T> {
    var selectedItem: T?
    var onItemSelected: (T) -> Unit
}
