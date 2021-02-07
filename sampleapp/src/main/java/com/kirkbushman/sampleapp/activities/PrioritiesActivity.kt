package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.callbacks.OnUpDelCallback
import com.kirkbushman.sampleapp.controllers.PrioritiesController
import com.kirkbushman.sampleapp.databinding.ActivityPrioritiesBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.TicketPriority
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PrioritiesActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

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

                DoAsync(
                    doWork = {

                        val priority = priorities[position]
                        client.deleteTicketPriority(priority)
                    },
                    onPost = {
                        showToast("Priority deleted!")
                    }
                )
            }
        })
    }

    private lateinit var binding: ActivityPrioritiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrioritiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.list.setHasFixedSize(true)
        binding.list.setController(controller)

        DoAsync(
            doWork = {
                priorities.addAll(client.ticketPrioritites() ?: listOf())
            },
            onPost = {
                controller.setItems(priorities)
            }
        )
    }
}
