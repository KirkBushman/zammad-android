package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.OnUpDelCallback
import com.kirkbushman.sampleapp.controllers.PrioritiesController
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.showToast
import com.kirkbushman.zammad.models.TicketPriority
import kotlinx.android.synthetic.main.activity_groups.*

class PrioritiesActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    private val priorities = ArrayList<TicketPriority>()
    private val controller by lazy {
        PrioritiesController(object : OnUpDelCallback {

            override fun onClick(position: Int) {

                val priority = priorities[position]
                PriorityActivity.start(this@PrioritiesActivity, priority)
            }

            override fun onUpdateClick(position: Int) {

                val priority = priorities[position]
                PriorityUpdateActivity.start(this@PrioritiesActivity, priority)
            }

            override fun onDeleteClick(position: Int) {

                doAsync(
                    doWork = {

                        val priority = priorities[position]
                        client?.deleteTicketPriority(priority)
                    },
                    onPost = {
                        showToast("Priority deleted!")
                    }
                )
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_priorities)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(
            doWork = {
                priorities.addAll(client?.ticketPrioritites() ?: listOf())
            },
            onPost = {
                controller.setItems(priorities)
            }
        )
    }
}
