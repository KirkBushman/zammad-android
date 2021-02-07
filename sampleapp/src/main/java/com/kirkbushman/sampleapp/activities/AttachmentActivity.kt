package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.ArticleAttachment
import com.kirkbushman.zammad.models.Ticket
import com.kirkbushman.zammad.models.TicketArticle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AttachmentActivity : AppCompatActivity(R.layout.activity_detail) {

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

    @Inject
    lateinit var client: ZammadClient

    private val ticket by lazy { intent.getParcelableExtra<Ticket>(PARAM_TICKET)!! }
    private val article by lazy { intent.getParcelableExtra<TicketArticle>(PARAM_ARTICLE)!! }
    private val attachment by lazy { intent.getParcelableExtra<ArticleAttachment>(PARAM_ATTACHMENT)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fileContent = ""
        DoAsync(
            doWork = {
                fileContent = client.ticketArticleAttachment(ticket, article, attachment) ?: ""
            },
            onPost = {
                binding.modelText.text = fileContent
            }
        )
    }
}
