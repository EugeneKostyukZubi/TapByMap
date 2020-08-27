package com.eugene.tapbymap.base.adapter

interface ActionsItemAdapter<T> {
    var actionCalled: T?
    var onItemActionSelected: (T) -> Unit
}