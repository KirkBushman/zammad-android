package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.Object

@EpoxyModelClass(layout = R.layout.item_object)
abstract class ObjectModel : EpoxyModelWithHolder<ObjectHolder>() {

    @EpoxyAttribute
    lateinit var `object`: Object

    @EpoxyAttribute(DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: ObjectHolder) {

        holder.objectName.text = `object`.name
        holder.objectDisplay.text = `object`.display
        holder.objectDataType.text = `object`.dataType
        holder.objectObject.text = `object`.`object`
        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: ObjectHolder) {

        holder.container.setOnClickListener(null)
    }
}

class ObjectHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val objectName by bind<TextView>(R.id.object_name)
    val objectDisplay by bind<TextView>(R.id.object_display)
    val objectDataType by bind<TextView>(R.id.object_data_type)
    val objectObject by bind<TextView>(R.id.object_object)
}
