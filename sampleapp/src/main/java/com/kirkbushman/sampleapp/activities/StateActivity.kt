package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.TicketState
import kotlinx.android.synthetic.main.activity_detail.*

class StateActivity : AppCompatActivity(R.layout.activity_detail) {

    companion object {

        private const val PARAM_STATE = "intent_param_state"

        fun start(context: Context, state: TicketState) {

            val intent = Intent(context, StateActivity::class.java)
            intent.putExtra(PARAM_STATE, state)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val state by lazy { intent.getParcelableExtra<TicketState>(PARAM_STATE)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var newState: TicketState? = null
        doAsync(
            doWork = {
                newState = client?.ticketState(state.id, true)
            },
            onPost = {
                model_text.text = newState.toString()
            }
        )
    }
}
