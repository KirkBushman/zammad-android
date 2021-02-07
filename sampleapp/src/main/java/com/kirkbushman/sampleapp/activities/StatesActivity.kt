package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.callbacks.OnUpDelCallback
import com.kirkbushman.sampleapp.controllers.StatesController
import com.kirkbushman.sampleapp.databinding.ActivityStatesBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.TicketState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StatesActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private val states = ArrayList<TicketState>()
    private val controller by lazy {
        StatesController(object : OnUpDelCallback {

            override fun onClick(position: Int) {

                val state = states[position]
                StateActivity.start(this@StatesActivity, state)
            }

            override fun onUpdateClick(position: Int) {

                val state = states[position]
                StateUpdateActivity.start(this@StatesActivity, state)
            }

            override fun onDeleteClick(position: Int) {

                DoAsync(
                    doWork = {

                        val state = states[position]
                        client.deleteTicketState(state)
                    },
                    onPost = {
                        showToast("State deleted!")
                    }
                )
            }
        })
    }

    private lateinit var binding: ActivityStatesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStatesBinding.inflate(layoutInflater)
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
                states.addAll(client.ticketStates() ?: listOf())
            },
            onPost = {
                controller.setItems(states)
            }
        )
    }
}
