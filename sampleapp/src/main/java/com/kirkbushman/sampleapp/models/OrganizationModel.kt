package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.Organization

@EpoxyModelClass(layout = R.layout.item_organization)
abstract class OrganizationModel : EpoxyModelWithHolder<OrganizationHolder>() {

    @EpoxyAttribute
    lateinit var organization: Organization

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: OrganizationHolder) {

        holder.organizationName.text = organization.name
        holder.organizationActive.text = organization.active.toString()
        holder.organizationCreated.text = organization.createdAt

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: OrganizationHolder) {
        holder.container.setOnClickListener(null)
    }
}

class OrganizationHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val organizationName by bind<TextView>(R.id.organization_name)
    val organizationActive by bind<TextView>(R.id.organization_active)
    val organizationCreated by bind<TextView>(R.id.organization_created)
}
