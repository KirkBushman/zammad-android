package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityArticleCreateBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Ticket
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticleCreateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_TICKET = "intent_param_ticket"

        fun start(context: Context, ticket: Ticket) {

            val intent = Intent(context, ArticleCreateActivity::class.java)
            intent.putExtra(PARAM_TICKET, ticket)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val ticket by lazy { intent.getParcelableExtra<Ticket>(PARAM_TICKET)!! }

    private lateinit var binding: ActivityArticleCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArticleCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bttnSubmit.setOnClickListener {

            val to = binding.articleTo.text.trim().toString()
            val cc = binding.articleCc.text.trim().toString()
            val subject = binding.articleSubject.text.trim().toString()
            val body = binding.articleBody.text.trim().toString()

            DoAsync(
                doWork = {

                    client.createTicketArticle(
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
