package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.controllers.ArticlesController
import com.kirkbushman.sampleapp.callbacks.OnArticleCallback
import com.kirkbushman.sampleapp.databinding.ActivityArticlesBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Ticket
import com.kirkbushman.zammad.models.TicketArticle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticlesActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_TICKET = "intent_param_ticket"

        fun start(context: Context, ticket: Ticket) {

            val intent = Intent(context, ArticlesActivity::class.java)
            intent.putExtra(PARAM_TICKET, ticket)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

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

    private lateinit var binding: ActivityArticlesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.list.setHasFixedSize(true)
        binding.list.setController(controller)

        binding.fab.setOnClickListener {

            ArticleCreateActivity.start(this, ticket)
        }

        DoAsync(
            doWork = {
                articles.addAll(client.ticketArticles(ticket) ?: listOf())
            },
            onPost = {
                controller.setItems(articles)
            }
        )
    }
}
