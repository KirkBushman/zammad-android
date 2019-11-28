package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.models.tag
import com.kirkbushman.zammad.models.Tag

class TagsController(callback: OnClickCallback) : BaseController<Tag>(callback) {

    override fun onItem(item: Tag) {

        tag {
            id(item.id)
            tag(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
        }
    }
}
