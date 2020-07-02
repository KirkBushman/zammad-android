package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.OnUpDelCallback
import com.kirkbushman.sampleapp.controllers.StatesController
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.showToast
import com.kirkbushman.zammad.models.TicketState
import kotlinx.android.synthetic.main.activity_groups.*

class StatesActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

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

                doAsync(
                    doWork = {

                        val state = states[position]
                        client?.deleteTicketState(state)
                    },
                    onPost = {
                        showToast("State deleted!")
                    }
                )
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_states)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(
            doWork = {
                states.addAll(client?.ticketStates() ?: listOf())
            },
            onPost = {
                controller.setItems(states)
            }
        )
    }
}
