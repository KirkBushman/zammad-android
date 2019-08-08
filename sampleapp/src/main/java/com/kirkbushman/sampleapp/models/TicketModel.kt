package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.Ticket

@EpoxyModelClass(layout = R.layout.item_ticket)
abstract class TicketModel : EpoxyModelWithHolder<TicketHolder>() {

    @EpoxyAttribute
    lateinit var ticket: Ticket

    @EpoxyAttribute
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: TicketHolder) {

        holder.ticketTitle.text = ticket.title
        holder.ticketId.text = ticket.id.toString()
        holder.ticketCreated.text = ticket.createdAt

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: TicketHolder) {
        holder.container.setOnClickListener(null)
    }
}

class TicketHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val ticketTitle by bind<TextView>(R.id.ticket_title)
    val ticketId by bind<TextView>(R.id.ticket_id)
    val ticketCreated by bind<TextView>(R.id.ticket_created)
}
