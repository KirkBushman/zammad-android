package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.`object`
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.zammad.models.Object

class ObjectsController(private val callback: OnClickCallback) : EpoxyController() {

    private val objects = ArrayList<Object>()

    fun setObjects(items: Collection<Object>) {
        this.objects.clear()
        this.objects.addAll(items)
        requestModelBuild()
    }

    override fun buildModels() {

        if (objects.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        objects.forEach {

            `object` {

                id(it.id)
                `object`(it)
                clickListener { _, _, _, position -> callback.onClick(position) }
            }
        }
    }
}