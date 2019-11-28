package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.models.overview
import com.kirkbushman.zammad.models.Overview

class OverviewsController(callback: OnClickCallback) : BaseController<Overview>(callback) {

    override fun onItem(item: Overview) {

        overview {
            id(item.id)
            overview(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
        }
    }
}
