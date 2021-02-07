package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.models.base.KotlinHolder
import com.kirkbushman.zammad.models.Overview

@EpoxyModelClass
abstract class OverviewModel : EpoxyModelWithHolder<OverviewHolder>() {

    @EpoxyAttribute
    lateinit var overview: Overview

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun getDefaultLayout(): Int {
        return R.layout.item_overview
    }

    override fun bind(holder: OverviewHolder) {

        holder.overviewName.text = overview.name
        holder.overviewActive.text = overview.active.toString()
        holder.overviewCreated.text = overview.createdAt

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: OverviewHolder) {
        holder.container.setOnClickListener(null)
    }
}

class OverviewHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val overviewName by bind<TextView>(R.id.overview_name)
    val overviewActive by bind<TextView>(R.id.overview_active)
    val overviewCreated by bind<TextView>(R.id.overview_created)
}
