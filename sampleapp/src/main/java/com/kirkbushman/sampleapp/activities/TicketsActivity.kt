package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.TicketsController
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Ticket
import kotlinx.android.synthetic.main.activity_tickets.*

class TicketsActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    private val tickets = ArrayList<Ticket>()
    private val controller by lazy { TicketsController() }

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

        doAsync(doWork = {

            tickets.addAll(client?.tickets() ?: listOf())
        }, onPost = {

            controller.setTickets(tickets)
        })
    }
}
