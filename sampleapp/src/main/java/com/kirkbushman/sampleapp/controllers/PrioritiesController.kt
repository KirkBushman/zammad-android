package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.priority
import com.kirkbushman.zammad.models.TicketPriority

class PrioritiesController(private val callback: OnClickCallback) : EpoxyController() {

    private val priorities = ArrayList<TicketPriority>()

    fun setPriorities(priorities: Collection<TicketPriority>) {
        this.priorities.clear()
        this.priorities.addAll(priorities)
        requestModelBuild()
    }

    override fun buildModels() {

        if (priorities.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        priorities.forEach {

            priority {
                id(it.id)
                priority(it)
                clickListener { _, _, _, position -> callback.onClick(position) }
            }
        }
    }
}
