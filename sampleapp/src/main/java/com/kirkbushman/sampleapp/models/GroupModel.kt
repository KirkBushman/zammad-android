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
import com.kirkbushman.zammad.models.Group

@EpoxyModelClass(layout = R.layout.item_group)
abstract class GroupModel : EpoxyModelWithHolder<GroupHolder>() {

    @EpoxyAttribute
    lateinit var group: Group

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var updateListener: View.OnClickListener
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var deleteListener: View.OnClickListener

    override fun bind(holder: GroupHolder) {

        holder.groupName.text = group.name
        holder.groupActive.text = group.active.toString()
        holder.groupCreated.text = group.createdAt

        holder.container.setOnClickListener(clickListener)

        holder.bttnUpdate.setOnClickListener(updateListener)
        holder.bttnDelete.setOnClickListener(deleteListener)
    }

    override fun unbind(holder: GroupHolder) {
        holder.container.setOnClickListener(null)
        holder.bttnUpdate.setOnClickListener(null)
        holder.bttnDelete.setOnClickListener(null)
    }
}

class GroupHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val groupName by bind<TextView>(R.id.group_name)
    val groupActive by bind<TextView>(R.id.group_active)
    val groupCreated by bind<TextView>(R.id.group_created)
    val bttnUpdate by bind<Button>(R.id.group_update)
    val bttnDelete by bind<Button>(R.id.group_delete)
}
