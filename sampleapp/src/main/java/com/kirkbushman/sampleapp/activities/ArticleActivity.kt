package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.TicketArticle
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_ARTICLE = "intent_param_article"

        fun start(context: Context, article: TicketArticle) {

            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra(PARAM_ARTICLE, article)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val article by lazy { intent.getParcelableExtra(PARAM_ARTICLE) as TicketArticle }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        var newArticle: TicketArticle? = null
        doAsync(doWork = {

            newArticle = client?.ticketArticle(article.id, true)
        }, onPost = {

            article_text.text = newArticle.toString()
        })
    }
}
