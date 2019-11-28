package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.models.ticket
import com.kirkbushman.zammad.models.Ticket

class TicketsController(callback: OnTicketCallback) : BaseController<Ticket>(callback) {

    override fun onItem(item: Ticket) {

        ticket {
            id(item.id)
            ticket(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
            articleListener { _, _, _, position -> (callback as OnTicketCallback).onArticleClick(position) }
            updateListener { _, _, _, position -> (callback as OnTicketCallback).onUpdateClick(position) }
            deleteListener { _, _, _, position -> (callback as OnTicketCallback).onDeleteClick(position) }
        }
    }
}
