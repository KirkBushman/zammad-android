package com.kirkbushman.sampleapp.models

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.TicketState

@EpoxyModelClass(layout = R.layout.item_state)
abstract class StateModel : EpoxyModelWithHolder<StateHolder>() {

    @EpoxyAttribute
    lateinit var state: TicketState

    override fun bind(holder: StateHolder) {

        holder.stateName.text = state.name
        holder.stateActive.text = state.active.toString()
        holder.stateCreated.text = state.createdAt
    }
}

class StateHolder : KotlinHolder() {

    val stateName by bind<TextView>(R.id.state_name)
    val stateActive by bind<TextView>(R.id.state_active)
    val stateCreated by bind<TextView>(R.id.state_created)
}
