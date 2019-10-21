package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.showToast
import com.kirkbushman.zammad.models.TicketState
import kotlinx.android.synthetic.main.activity_state_update.*

class StateUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_STATE = "intent_param_state"

        fun start(context: Context, state: TicketState) {

            val intent = Intent(context, StateUpdateActivity::class.java)
            intent.putExtra(PARAM_STATE, state)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val state by lazy { intent.getParcelableExtra<TicketState>(PARAM_STATE)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_update)

        state_name.setText(state.name)
        state_active.isChecked = state.active
        state_note.setText(state.note)

        bttn_submit.setOnClickListener {

            val name = state_name.text.toString()
            val active = state_active.isChecked
            val note = state_note.text.toString()

            doAsync(doWork = {

                client?.updateTicketState(
                    id = state.id,
                    name = name,
                    active = active,
                    note = note
                )
            }, onPost = {

                showToast("TicketState Updated!")
            })
        }
    }
}
