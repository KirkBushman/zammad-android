package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.callbacks.OnClickCallback
import com.kirkbushman.sampleapp.models.empty

abstract class BaseController<T>(protected val callback: OnClickCallback) : EpoxyController() {

    private val items = ArrayList<T>()

    fun setItems(items: Collection<T>) {
        this.items.clear()
        this.items.addAll(items)
        requestModelBuild()
    }

    override fun buildModels() {

        if (items.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        items.forEach { onItem(it) }
    }

    abstract fun onItem(item: T)
}
