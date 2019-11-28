package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Ticket
import kotlinx.android.synthetic.main.activity_detail.*

class TicketActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_TICKET = "intent_param_ticket"

        fun start(context: Context, ticket: Ticket) {

            val intent = Intent(context, TicketActivity::class.java)
            intent.putExtra(PARAM_TICKET, ticket)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val ticket by lazy { intent.getParcelableExtra(PARAM_TICKET) as Ticket }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var newTicket: Ticket? = null
        doAsync(doWork = {

            newTicket = client?.ticket(ticket.id, true)
        }, onPost = {

            model_text.text = newTicket.toString()
        })
    }
}
