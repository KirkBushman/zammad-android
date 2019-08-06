package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.state
import com.kirkbushman.zammad.models.TicketState

class StatesController : EpoxyController() {

    private val states = ArrayList<TicketState>()

    fun setStates(states: Collection<TicketState>) {
        this.states.clear()
        this.states.addAll(states)
        requestModelBuild()
    }

    override fun buildModels() {

        if (states.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        states.forEach {

            state {
                id(it.id)
                state(it)
            }
        }
    }
}
