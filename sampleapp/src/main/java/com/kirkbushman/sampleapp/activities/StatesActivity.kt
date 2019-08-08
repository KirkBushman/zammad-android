package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.controllers.StatesController
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.TicketState
import kotlinx.android.synthetic.main.activity_groups.*

class StatesActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    private val states = ArrayList<TicketState>()
    private val controller by lazy {
        StatesController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val state = states[position]
                StateActivity.start(this@StatesActivity, state)
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

        doAsync(doWork = {

            states.addAll(client?.ticketStates() ?: listOf())
        }, onPost = {

            controller.setStates(states)
        })
    }
}
