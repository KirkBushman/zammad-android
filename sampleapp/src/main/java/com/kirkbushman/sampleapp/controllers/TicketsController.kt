package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.ticket
import com.kirkbushman.zammad.models.Ticket

class TicketsController : EpoxyController() {

    private val tickets = ArrayList<Ticket>()

    fun setTickets(tickets: Collection<Ticket>) {
        this.tickets.clear()
        this.tickets.addAll(tickets)
        requestModelBuild()
    }

    override fun buildModels() {

        if (tickets.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        tickets.forEach {

            ticket {
                id(it.id)
                ticket(it)
            }
        }
    }
}
