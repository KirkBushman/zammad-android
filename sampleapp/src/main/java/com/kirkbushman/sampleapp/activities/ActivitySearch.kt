package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.callbacks.OnTicketCallback
import com.kirkbushman.sampleapp.controllers.TicketsController
import com.kirkbushman.sampleapp.databinding.ActivitySearchBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.SearchResult
import com.kirkbushman.zammad.models.Ticket
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActivitySearch : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private var searchResult: SearchResult? = null
    private val tickets = ArrayList<Ticket>()
    private val controller by lazy {
        TicketsController(object : OnTicketCallback {

            override fun onClick(position: Int) {

                val ticket = tickets[position]
                TicketActivity.start(this@ActivitySearch, ticket)
            }

            override fun onArticleClick(position: Int) {

                val ticket = tickets[position]
                ArticlesActivity.start(this@ActivitySearch, ticket)
            }

            override fun onUpdateClick(position: Int) {

                val ticket = tickets[position]
                TicketUpdateActivity.start(this@ActivitySearch, ticket)
            }

            override fun onDeleteClick(position: Int) {

                DoAsync(
                    doWork = {

                        val ticket = tickets[position]
                        client.deleteTicket(ticket.id)
                    },
                    onPost = {
                        Toast.makeText(this@ActivitySearch, "Ticket deleted!", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        })
    }

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.list.setHasFixedSize(true)
        binding.list.setController(controller)

        binding.searchBttn.setOnClickListener {

            val query = binding.searchEdit.text.trim().toString()
            if (query != "") {

                tickets.clear()

                DoAsync(
                    doWork = {

                        searchResult = client.searchTickets(query = "title:$query", page = 1, perPage = 20)
                        tickets.addAll(searchResult?.assets?.tickets?.values ?: listOf())
                    },
                    onPost = {
                        controller.setItems(tickets)
                    }
                )
            }
        }
    }
}
