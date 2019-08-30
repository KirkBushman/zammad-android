package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.ArticleAttachment
import com.kirkbushman.zammad.models.Ticket
import com.kirkbushman.zammad.models.TicketArticle
import kotlinx.android.synthetic.main.activity_attachment.*

class AttachmentActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_TICKET = "intent_param_ticket"
        private const val PARAM_ARTICLE = "intent_param_article"
        private const val PARAM_ATTACHMENT = "intent_param_attachment"

        fun start(context: Context, ticket: Ticket, article: TicketArticle, attachment: ArticleAttachment) {

            val intent = Intent(context, AttachmentActivity::class.java)
            intent.putExtra(PARAM_TICKET, ticket)
            intent.putExtra(PARAM_ARTICLE, article)
            intent.putExtra(PARAM_ATTACHMENT, attachment)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }

    private val ticket by lazy { intent.getParcelableExtra(PARAM_TICKET) as Ticket }
    private val article by lazy { intent.getParcelableExtra(PARAM_ARTICLE) as TicketArticle }
    private val attachment by lazy { intent.getParcelableExtra(PARAM_ATTACHMENT) as ArticleAttachment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attachment)

        var fileContent = ""
        doAsync(doWork = {

            fileContent = client?.ticketArticleAttachment(ticket, article, attachment) ?: ""
        }, onPost = {

            attachment_text.text = fileContent
        })
    }
}
