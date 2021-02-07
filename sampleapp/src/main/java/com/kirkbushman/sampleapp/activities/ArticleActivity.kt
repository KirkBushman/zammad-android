package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.TicketArticle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_ARTICLE = "intent_param_article"

        fun start(context: Context, article: TicketArticle) {

            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra(PARAM_ARTICLE, article)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val article by lazy { intent.getParcelableExtra<TicketArticle>(PARAM_ARTICLE)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newArticle: TicketArticle? = null
        DoAsync(
            doWork = {
                newArticle = client.ticketArticle(article.id, true)
            },
            onPost = {
                binding.modelText.text = newArticle.toString()
            }
        )
    }
}
