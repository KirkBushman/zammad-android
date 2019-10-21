package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.group
import com.kirkbushman.zammad.models.Group

class GroupsController(private val callback: OnGroupCallback) : EpoxyController() {

    private val groups = ArrayList<Group>()

    fun setGroups(groups: Collection<Group>) {
        this.groups.clear()
        this.groups.addAll(groups)
        requestModelBuild()
    }

    override fun buildModels() {

        if (groups.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        groups.forEach {

            group {
                id(it.id)
                group(it)
                clickListener { _, _, _, position -> callback.onClick(position) }
                updateListener { _, _, _, position -> callback.onUpdateClick(position) }
                deleteListener { _, _, _, position -> callback.onDeleteClick(position) }
            }
        }
    }
}
