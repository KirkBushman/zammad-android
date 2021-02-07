package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.callbacks.OnClickCallback
import com.kirkbushman.sampleapp.models.role
import com.kirkbushman.zammad.models.Role

class RolesController(callback: OnClickCallback) : BaseController<Role>(callback) {

    override fun onItem(item: Role) {

        role {
            id(item.id)
            role(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
        }
    }
}
