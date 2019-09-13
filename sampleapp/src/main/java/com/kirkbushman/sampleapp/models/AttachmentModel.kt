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

        if (attachment.hasContentType()) {
            holder.attachmentContentType.visibility = View.VISIBLE
            holder.attachmentContentType.text = attachment.getContentType() ?: ""
        } else {
            holder.attachmentContentType.visibility = View.GONE
        }

        if (attachment.hasMimeType()) {
            holder.attachmentMimeType.visibility = View.VISIBLE
            holder.attachmentMimeType.text = attachment.getMimeType() ?: ""
        } else {
            holder.attachmentMimeType.visibility = View.GONE
        }

        if (attachment.hasCharset()) {
            holder.attachmentCharset.visibility = View.VISIBLE
            holder.attachmentCharset.text = attachment.getCharset() ?: ""
        } else {
            holder.attachmentCharset.visibility = View.GONE
        }

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: AttachmentHolder) {
        holder.container.setOnClickListener(null)
    }
}

class AttachmentHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val attachmentFilename by bind<TextView>(R.id.attachment_filename)
    val attachmentContentType by bind<TextView>(R.id.attachment_contenttype)
    val attachmentMimeType by bind<TextView>(R.id.attachment_mimetype)
    val attachmentCharset by bind<TextView>(R.id.attachment_charset)
    val attachmentFilesize by bind<TextView>(R.id.attachment_filesize)
}
