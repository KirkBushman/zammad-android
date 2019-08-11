package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.Role

@EpoxyModelClass(layout = R.layout.item_role)
abstract class RoleModel : EpoxyModelWithHolder<RoleHolder>() {

    @EpoxyAttribute
    lateinit var role: Role

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: RoleHolder) {

        holder.roleName.text = role.name
        holder.roleActive.text = role.active.toString()
        holder.roleCreated.text = role.createdAt

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: RoleHolder) {
        holder.container.setOnClickListener(null)
    }
}

class RoleHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val roleName by bind<TextView>(R.id.role_name)
    val roleActive by bind<TextView>(R.id.role_active)
    val roleCreated by bind<TextView>(R.id.role_created)
}
