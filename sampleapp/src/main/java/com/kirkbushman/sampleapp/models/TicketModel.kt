package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.models.base.KotlinHolder
import com.kirkbushman.zammad.models.Ticket

@EpoxyModelClass
abstract class TicketModel : EpoxyModelWithHolder<TicketHolder>() {

    @EpoxyAttribute
    lateinit var ticket: Ticket

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var articleListener: View.OnClickListener
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var updateListener: View.OnClickListener
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var deleteListener: View.OnClickListener

    override fun getDefaultLayout(): Int {
        return R.layout.item_ticket
    }

    override fun bind(holder: TicketHolder) {

        holder.ticketTitle.text = ticket.title
        holder.ticketId.text = ticket.id.toString()
        holder.ticketCreated.text = ticket.createdAt

        holder.ticketArticle.text = ticket.articleCount.toString().plus(" articles")

        holder.container.setOnClickListener(clickListener)
        holder.ticketArticle.setOnClickListener(articleListener)
        holder.ticketUpdate.setOnClickListener(updateListener)
        holder.ticketDelete.setOnClickListener(deleteListener)
    }

    override fun unbind(holder: TicketHolder) {
        holder.container.setOnClickListener(null)
        holder.ticketArticle.setOnClickListener(null)
        holder.ticketUpdate.setOnClickListener(null)
        holder.ticketDelete.setOnClickListener(null)
    }
}

class TicketHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val ticketTitle by bind<TextView>(R.id.ticket_title)
    val ticketId by bind<TextView>(R.id.ticket_id)
    val ticketCreated by bind<TextView>(R.id.ticket_created)
    val ticketArticle by bind<Button>(R.id.ticket_articles)
    val ticketUpdate by bind<Button>(R.id.ticket_update)
    val ticketDelete by bind<Button>(R.id.ticket_delete)
}
