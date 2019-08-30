package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.article
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.zammad.models.TicketArticle

class ArticlesController(private val callback: OnArticleCallback) : EpoxyController() {

    private val articles = ArrayList<TicketArticle>()

    fun setArticles(articles: Collection<TicketArticle>) {
        this.articles.clear()
        this.articles.addAll(articles)
        requestModelBuild()
    }

    override fun buildModels() {

        if (articles.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        articles.forEach {

            article {
                id(it.id)
                article(it)
                clickListener { _, _, _, position -> callback.onClick(position) }
                attachmentsClick { _, _, _, position -> callback.onAttachmentsClick(position) }
            }
        }
    }
}
