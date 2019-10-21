package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.user
import com.kirkbushman.zammad.models.User

class UsersController(private val callback: OnUpDelCallback) : EpoxyController() {

    private val users = ArrayList<User>()

    fun setUsers(users: Collection<User>) {
        this.users.clear()
        this.users.addAll(users)
        requestModelBuild()
    }

    override fun buildModels() {

        if (users.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        users.forEach {

            user {
                id(it.id)
                user(it)
                clickListener { _, _, _, position -> callback.onClick(position) }
                updateListener { _, _, _, position -> callback.onUpdateClick(position) }
                deleteListener { _, _, _, position -> callback.onDeleteClick(position) }
            }
        }
    }
}
