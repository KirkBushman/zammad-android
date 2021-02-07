package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Ticket
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TicketActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_TICKET = "intent_param_ticket"

        fun start(context: Context, ticket: Ticket) {

            val intent = Intent(context, TicketActivity::class.java)
            intent.putExtra(PARAM_TICKET, ticket)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val ticket by lazy { intent.getParcelableExtra<Ticket>(PARAM_TICKET)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newTicket: Ticket? = null
        DoAsync(
            doWork = {
                newTicket = client.ticket(ticket.id, true)
            },
            onPost = {
                binding.modelText.text = newTicket.toString()
            }
        )
    }
}
