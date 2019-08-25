package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.tag
import com.kirkbushman.zammad.models.Tag

class TagsController(private val callback: OnClickCallback) : EpoxyController() {

    private val tags = ArrayList<Tag>()

    fun setTags(tags: Collection<Tag>) {
        this.tags.clear()
        this.tags.addAll(tags)
        requestModelBuild()
    }

    override fun buildModels() {

        if (tags.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        tags.forEach {

            tag {
                id(it.id)
                tag(it)
                clickListener { _, _, _, position -> callback.onClick(position) }
            }
        }
    }
}
