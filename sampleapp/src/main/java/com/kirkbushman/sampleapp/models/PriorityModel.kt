package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.TicketPriority

@EpoxyModelClass(layout = R.layout.item_priority)
abstract class PriorityModel : EpoxyModelWithHolder<PriorityHolder>() {

    @EpoxyAttribute
    lateinit var priority: TicketPriority

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var updateListener: View.OnClickListener
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var deleteListener: View.OnClickListener

    override fun bind(holder: PriorityHolder) {

        holder.priorityName.text = priority.name
        holder.priorityActive.text = priority.active.toString()
        holder.priorityCreated.text = priority.createdAt

        holder.container.setOnClickListener(clickListener)

        holder.priorityUpdate.setOnClickListener(updateListener)
        holder.priorityDelete.setOnClickListener(deleteListener)
    }

    override fun unbind(holder: PriorityHolder) {
        holder.container.setOnClickListener(null)

        holder.priorityUpdate.setOnClickListener(null)
        holder.priorityDelete.setOnClickListener(null)
    }
}

class PriorityHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val priorityName by bind<TextView>(R.id.priority_name)
    val priorityActive by bind<TextView>(R.id.priority_active)
    val priorityCreated by bind<TextView>(R.id.priority_created)

    val priorityUpdate by bind<Button>(R.id.priority_update)
    val priorityDelete by bind<Button>(R.id.priority_delete)
}
