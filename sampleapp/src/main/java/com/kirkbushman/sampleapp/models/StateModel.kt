package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
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

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: StateHolder) {

        holder.stateName.text = state.name
        holder.stateActive.text = state.active.toString()
        holder.stateCreated.text = state.createdAt

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: StateHolder) {
        holder.container.setOnClickListener(null)
    }
}

class StateHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val stateName by bind<TextView>(R.id.state_name)
    val stateActive by bind<TextView>(R.id.state_active)
    val stateCreated by bind<TextView>(R.id.state_created)
}
