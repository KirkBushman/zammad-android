package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.ArticleAttachment

@EpoxyModelClass(layout = R.layout.item_attachment)
abstract class AttachmentModel : EpoxyModelWithHolder<AttachmentHolder>() {

    @EpoxyAttribute
    lateinit var attachment: ArticleAttachment

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: AttachmentHolder) {

        holder.attachmentFilename.text = attachment.filename
        holder.attachmentFilesize.text = attachment.size.toString()

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: AttachmentHolder) {
        holder.container.setOnClickListener(null)
    }
}

class AttachmentHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val attachmentFilename by bind<TextView>(R.id.attachment_filename)
    val attachmentFilesize by bind<TextView>(R.id.attachment_filesize)
}
