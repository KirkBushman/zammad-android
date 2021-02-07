package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.callbacks.OnClickCallback
import com.kirkbushman.sampleapp.models.attachment
import com.kirkbushman.zammad.models.ArticleAttachment

class AttachmentsController(callback: OnClickCallback) : BaseController<ArticleAttachment>(callback) {

    override fun onItem(item: ArticleAttachment) {

        attachment {
            id(item.id)
            attachment(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
        }
    }
}
