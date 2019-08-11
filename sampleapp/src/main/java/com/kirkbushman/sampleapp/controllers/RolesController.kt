package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.role
import com.kirkbushman.zammad.models.Role

class RolesController(private val callback: OnClickCallback) : EpoxyController() {

    private val roles = ArrayList<Role>()

    fun setRoles(roles: Collection<Role>) {
        this.roles.clear()
        this.roles.addAll(roles)
        requestModelBuild()
    }

    override fun buildModels() {

        if (roles.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        roles.forEach {

            role {
                id(it.id)
                role(it)
                clickListener { _, _, _, position -> callback.onClick(position) }
            }
        }
    }
}
