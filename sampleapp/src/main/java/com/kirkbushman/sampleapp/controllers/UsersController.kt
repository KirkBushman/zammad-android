package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.callbacks.OnUpDelCallback
import com.kirkbushman.sampleapp.models.user
import com.kirkbushman.zammad.models.User

class UsersController(callback: OnUpDelCallback) : BaseController<User>(callback) {

    override fun onItem(item: User) {

        user {
            id(item.id)
            user(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
            updateListener { _, _, _, position -> (callback as OnUpDelCallback).onUpdateClick(position) }
            deleteListener { _, _, _, position -> (callback as OnUpDelCallback).onDeleteClick(position) }
        }
    }
}
