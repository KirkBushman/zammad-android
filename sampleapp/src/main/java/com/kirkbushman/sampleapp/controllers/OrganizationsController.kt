package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.organization
import com.kirkbushman.zammad.models.Organization

class OrganizationsController(private val callback: OnUpDelCallback) : EpoxyController() {

    private val organizations = ArrayList<Organization>()

    fun setOrganizations(organizations: Collection<Organization>) {
        this.organizations.clear()
        this.organizations.addAll(organizations)
        requestModelBuild()
    }

    override fun buildModels() {

        if (organizations.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        organizations.forEach {

            organization {
                id(it.id)
                organization(it)
                clickListener { _, _, _, position -> callback.onClick(position) }
                updateListener { _, _, _, position -> callback.onUpdateClick(position) }
                deleteListener { _, _, _, position -> callback.onDeleteClick(position) }
            }
        }
    }
}
