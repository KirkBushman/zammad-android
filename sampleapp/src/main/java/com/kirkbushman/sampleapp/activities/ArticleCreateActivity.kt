package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Ticket
import kotlinx.android.synthetic.main.activity_article_create.*

class ArticleCreateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_TICKET = "intent_param_ticket"

        fun start(context: Context, ticket: Ticket) {

            val intent = Intent(context, ArticleCreateActivity::class.java)
            intent.putExtra(PARAM_TICKET, ticket)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val ticket by lazy { intent.getParcelableExtra<Ticket>(PARAM_TICKET)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_create)

        bttn_submit.setOnClickListener {

            val to = article_to.text.trim().toString()
            val cc = article_cc.text.trim().toString()
            val subject = article_subject.text.trim().toString()
            val body = article_body.text.trim().toString()

            doAsync(
                doWork = {

                    client?.createTicketArticle(
                        ticketId = ticket.id,

                        to = to,
                        cc = cc,
                        subject = subject,
                        body = body,
                        contentType = "plain/text",
                        type = "note",
                        internal = false,
                        timeUnit = "12"
                    )
                },
                onPost = {
                    Toast.makeText(this, "Article Created!", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
