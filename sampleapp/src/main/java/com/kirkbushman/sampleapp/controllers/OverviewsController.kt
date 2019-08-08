package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.overview
import com.kirkbushman.zammad.models.Overview

class OverviewsController(private val callback: OnClickCallback) : EpoxyController() {

    private val overviews = ArrayList<Overview>()

    fun setOverviews(overviews: Collection<Overview>) {
        this.overviews.clear()
        this.overviews.addAll(overviews)
        requestModelBuild()
    }

    override fun buildModels() {

        if (overviews.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        overviews.forEach {

            overview {
                id(it.id)
                overview(it)
                clickListener { _, _, _, position -> callback.onClick(position) }
            }
        }
    }
}
