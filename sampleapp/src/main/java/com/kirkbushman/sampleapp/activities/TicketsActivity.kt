package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.OnTicketCallback
import com.kirkbushman.sampleapp.controllers.TicketsController
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Ticket
import kotlinx.android.synthetic.main.activity_tickets.*

class TicketsActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    private val tickets = ArrayList<Ticket>()
    private val controller by lazy {
        TicketsController(object : OnTicketCallback {

            override fun onClick(position: Int) {

                val ticket = tickets[position]
                TicketActivity.start(this@TicketsActivity, ticket)
            }

            override fun onArticleClick(position: Int) {

                val ticket = tickets[position]
                ArticlesActivity.start(this@TicketsActivity, ticket)
            }

            override fun onUpdateClick(position: Int) {

                val ticket = tickets[position]
                TicketUpdateActivity.start(this@TicketsActivity, ticket)
            }

            override fun onDeleteClick(position: Int) {

                doAsync(doWork = {

                    val ticket = tickets[position]
                    client?.deleteTicket(ticket.id)
                }, onPost = {

                    Toast.makeText(this@TicketsActivity, "Ticket deleted!", Toast.LENGTH_SHORT).show()
                })
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        fab.setOnClickListener {

            TicketCreateActivity.start(this)
        }

        doAsync(doWork = {

            tickets.addAll(client?.tickets() ?: listOf())
        }, onPost = {

            controller.setItems(tickets)
        })
    }
}
