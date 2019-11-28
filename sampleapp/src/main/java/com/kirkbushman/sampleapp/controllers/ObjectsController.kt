package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.models.`object`
import com.kirkbushman.zammad.models.Object

class ObjectsController(callback: OnClickCallback) : BaseController<Object>(callback) {

    override fun onItem(item: Object) {

        `object` {

            id(item.id)
            `object`(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
        }
    }
}
