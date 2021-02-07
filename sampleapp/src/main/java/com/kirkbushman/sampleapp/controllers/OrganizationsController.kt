package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.callbacks.OnUpDelCallback
import com.kirkbushman.sampleapp.models.organization
import com.kirkbushman.zammad.models.Organization

class OrganizationsController(callback: OnUpDelCallback) : BaseController<Organization>(callback) {

    override fun onItem(item: Organization) {

        organization {
            id(item.id)
            organization(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
            updateListener { _, _, _, position -> (callback as OnUpDelCallback).onUpdateClick(position) }
            deleteListener { _, _, _, position -> (callback as OnUpDelCallback).onDeleteClick(position) }
        }
    }
}
