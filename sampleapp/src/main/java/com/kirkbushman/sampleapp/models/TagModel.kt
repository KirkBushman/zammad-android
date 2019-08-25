package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.Tag

@EpoxyModelClass(layout = R.layout.item_tag)
abstract class TagModel : EpoxyModelWithHolder<TagHolder>() {

    @EpoxyAttribute
    lateinit var tag: Tag

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: TagHolder) {

        holder.tagName.text = tag.name
        holder.tagCount.text = tag.count.toString()

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: TagHolder) {
        holder.container.setOnClickListener(null)
    }
}

class TagHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val tagName by bind<TextView>(R.id.tag_name)
    val tagCount by bind<TextView>(R.id.tag_count)
}
