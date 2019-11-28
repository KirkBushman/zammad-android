package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.models.priority
import com.kirkbushman.zammad.models.TicketPriority

class PrioritiesController(callback: OnUpDelCallback) : BaseController<TicketPriority>(callback) {

    override fun onItem(item: TicketPriority) {

        priority {
            id(item.id)
            priority(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
            updateListener { _, _, _, position -> (callback as OnUpDelCallback).onUpdateClick(position) }
            deleteListener { _, _, _, position -> (callback as OnUpDelCallback).onDeleteClick(position) }
        }
    }
}
