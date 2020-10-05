package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.controllers.AttachmentsController
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.zammad.models.ArticleAttachment
import com.kirkbushman.zammad.models.Ticket
import com.kirkbushman.zammad.models.TicketArticle
import kotlinx.android.synthetic.main.activity_attachments.*

class AttachmentsActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_TICKET = "intent_param_ticket"
        private const val PARAM_ARTICLE = "intent_param_article"

        fun start(context: Context, ticket: Ticket, article: TicketArticle) {

            val intent = Intent(context, AttachmentsActivity::class.java)
            intent.putExtra(PARAM_TICKET, ticket)
            intent.putExtra(PARAM_ARTICLE, article)

            context.startActivity(intent)
        }
    }

    private val ticket by lazy { intent.getParcelableExtra<Ticket>(PARAM_TICKET)!! }
    private val article by lazy { intent.getParcelableExtra<TicketArticle>(PARAM_ARTICLE)!! }

    private val attachments = ArrayList<ArticleAttachment>()

    private val controller by lazy {
        AttachmentsController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val attachment = attachments[position]
                AttachmentActivity.start(this@AttachmentsActivity, ticket, article, attachment)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attachments)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        attachments.addAll(article.attachments)
        controller.setItems(attachments)
    }
}
