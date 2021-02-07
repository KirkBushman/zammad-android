package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.models.base.KotlinHolder
import com.kirkbushman.zammad.models.TicketArticle

@EpoxyModelClass
abstract class ArticleModel : EpoxyModelWithHolder<ArticleHolder>() {

    @EpoxyAttribute
    lateinit var article: TicketArticle

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var attachmentsClick: View.OnClickListener

    override fun getDefaultLayout(): Int {
        return R.layout.item_article
    }

    override fun bind(holder: ArticleHolder) {

        holder.articleTo.text = article.to
        holder.articleSubject.text = article.subject
        holder.articleText.text = article.body

        if (article.hasAttachments()) {
            holder.attachmentsBttn.visibility = View.VISIBLE

            val bttnText = "${article.attachments.size} Attachments"
            holder.attachmentsBttn.setText(bttnText)
        } else {
            holder.attachmentsBttn.visibility = View.GONE
        }

        holder.container.setOnClickListener(clickListener)
        holder.attachmentsBttn.setOnClickListener(attachmentsClick)
    }

    override fun unbind(holder: ArticleHolder) {
        holder.attachmentsBttn.visibility = View.GONE

        holder.container.setOnClickListener(null)
        holder.attachmentsBttn.setOnClickListener(null)
    }
}

class ArticleHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val articleTo by bind<TextView>(R.id.article_to)
    val articleSubject by bind<TextView>(R.id.article_subject)
    val articleText by bind<TextView>(R.id.article_text)
    val attachmentsBttn by bind<Button>(R.id.attachments_bttn)
}
