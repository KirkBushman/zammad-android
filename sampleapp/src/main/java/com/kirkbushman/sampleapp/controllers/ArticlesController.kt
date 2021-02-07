package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.callbacks.OnArticleCallback
import com.kirkbushman.sampleapp.models.article
import com.kirkbushman.zammad.models.TicketArticle

class ArticlesController(callback: OnArticleCallback) : BaseController<TicketArticle>(callback) {

    override fun onItem(item: TicketArticle) {

        article {
            id(item.id)
            article(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
            attachmentsClick { _, _, _, position -> (callback as OnArticleCallback).onAttachmentsClick(position) }
        }
    }
}
