package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.models.state
import com.kirkbushman.zammad.models.TicketState

class StatesController(callback: OnUpDelCallback) : BaseController<TicketState>(callback) {

    override fun onItem(item: TicketState) {

        state {
            id(item.id)
            state(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
            updateListener { _, _, _, position -> (callback as OnUpDelCallback).onUpdateClick(position) }
            deleteListener { _, _, _, position -> (callback as OnUpDelCallback).onDeleteClick(position) }
        }
    }
}
