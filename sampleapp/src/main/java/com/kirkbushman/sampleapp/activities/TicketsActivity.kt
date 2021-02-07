package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.callbacks.OnTicketCallback
import com.kirkbushman.sampleapp.controllers.TicketsController
import com.kirkbushman.sampleapp.databinding.ActivityTicketsBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Ticket
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TicketsActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

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

                DoAsync(
                    doWork = {

                        val ticket = tickets[position]
                        client.deleteTicket(ticket.id)
                    },
                    onPost = {
                        Toast.makeText(this@TicketsActivity, "Ticket deleted!", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        })
    }

    private lateinit var binding: ActivityTicketsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.list.setHasFixedSize(true)
        binding.list.setController(controller)

        binding.fab.setOnClickListener {

            TicketCreateActivity.start(this)
        }

        DoAsync(
            doWork = {
                tickets.addAll(client.tickets() ?: listOf())
            },
            onPost = {
                controller.setItems(tickets)
            }
        )
    }
}
