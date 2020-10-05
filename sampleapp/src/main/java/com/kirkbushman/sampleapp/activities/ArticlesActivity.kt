package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.ArticlesController
import com.kirkbushman.sampleapp.controllers.OnArticleCallback
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Ticket
import com.kirkbushman.zammad.models.TicketArticle
import kotlinx.android.synthetic.main.activity_tickets.*

class ArticlesActivity : AppCompatActivity(R.layout.activity_articles) {

    companion object {

        private const val PARAM_TICKET = "intent_param_ticket"

        fun start(context: Context, ticket: Ticket) {

            val intent = Intent(context, ArticlesActivity::class.java)
            intent.putExtra(PARAM_TICKET, ticket)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val ticket by lazy { intent.getParcelableExtra<Ticket>(PARAM_TICKET)!! }

    private val articles = ArrayList<TicketArticle>()
    private val controller by lazy {
        ArticlesController(object : OnArticleCallback {

            override fun onClick(position: Int) {

                val article = articles[position]
                ArticleActivity.start(this@ArticlesActivity, article)
            }

            override fun onAttachmentsClick(position: Int) {

                val article = articles[position]
                AttachmentsActivity.start(this@ArticlesActivity, ticket, article)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        fab.setOnClickListener {

            ArticleCreateActivity.start(this, ticket)
        }

        doAsync(
            doWork = {
                articles.addAll(client?.ticketArticles(ticket) ?: listOf())
            },
            onPost = {
                controller.setItems(articles)
            }
        )
    }
}
