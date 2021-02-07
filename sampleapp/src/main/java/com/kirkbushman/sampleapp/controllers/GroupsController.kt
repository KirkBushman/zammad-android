package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.callbacks.OnUpDelCallback
import com.kirkbushman.sampleapp.models.group
import com.kirkbushman.zammad.models.Group

class GroupsController(callback: OnUpDelCallback) : BaseController<Group>(callback) {

    override fun onItem(item: Group) {

        group {
            id(item.id)
            group(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
            updateListener { _, _, _, position -> (callback as OnUpDelCallback).onUpdateClick(position) }
            deleteListener { _, _, _, position -> (callback as OnUpDelCallback).onDeleteClick(position) }
        }
    }
}
