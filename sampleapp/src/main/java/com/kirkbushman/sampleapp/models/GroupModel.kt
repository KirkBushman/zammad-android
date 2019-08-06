package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.Group

@EpoxyModelClass(layout = R.layout.item_group)
abstract class GroupModel : EpoxyModelWithHolder<GroupHolder>() {

    @EpoxyAttribute
    lateinit var group: Group

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: GroupHolder) {

        holder.groupName.text = group.name
        holder.groupActive.text = group.active.toString()
        holder.groupCreated.text = group.createdAt

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: GroupHolder) {
        holder.container.setOnClickListener(null)
    }
}

class GroupHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val groupName by bind<TextView>(R.id.group_name)
    val groupActive by bind<TextView>(R.id.group_active)
    val groupCreated by bind<TextView>(R.id.group_created)
}
