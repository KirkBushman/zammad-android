package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.TicketArticle

@EpoxyModelClass(layout = R.layout.item_article)
abstract class ArticleModel : EpoxyModelWithHolder<ArticleHolder>() {

    @EpoxyAttribute
    lateinit var article: TicketArticle

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: ArticleHolder) {

        holder.articleTo.text = article.to
        holder.articleSubject.text = article.subject
        holder.articleText.text = article.body

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: ArticleHolder) {
        holder.container.setOnClickListener(null)
    }
}

class ArticleHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val articleTo by bind<TextView>(R.id.article_to)
    val articleSubject by bind<TextView>(R.id.article_subject)
    val articleText by bind<TextView>(R.id.article_text)
}
