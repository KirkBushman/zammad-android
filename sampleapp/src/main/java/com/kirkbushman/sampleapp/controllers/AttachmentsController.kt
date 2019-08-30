package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.attachment
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.zammad.models.ArticleAttachment

class AttachmentsController(private val callback: OnClickCallback) : EpoxyController() {

    private val attachments = ArrayList<ArticleAttachment>()

    fun setAttachments(attachments: Collection<ArticleAttachment>) {
        this.attachments.clear()
        this.attachments.addAll(attachments)
        requestModelBuild()
    }

    override fun buildModels() {

        if (attachments.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        attachments.forEach {

            attachment {
                id(it.id)
                attachment(it)
                clickListener { _, _, _, position -> callback.onClick(position) }
            }
        }
    }
}
