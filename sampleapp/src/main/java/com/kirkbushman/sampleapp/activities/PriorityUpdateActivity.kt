package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.showToast
import com.kirkbushman.zammad.models.TicketPriority
import kotlinx.android.synthetic.main.activity_priority_update.*

class PriorityUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_PRIORITY = "intent_param_ticket_priority"

        fun start(context: Context, ticketPriority: TicketPriority) {

            val intent = Intent(context, PriorityUpdateActivity::class.java)
            intent.putExtra(PARAM_PRIORITY, ticketPriority)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val priority by lazy { intent.getParcelableExtra<TicketPriority>(PARAM_PRIORITY)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_priority_update)

        priority_name.setText(priority.name)
        priority_active.isChecked = priority.active
        priority_note.setText(priority.note)

        bttn_submit.setOnClickListener {

            val name = priority_name.text.toString()
            val active = priority_active.isChecked
            val note = priority_note.text.toString()

            doAsync(
                doWork = {

                    client?.updateTicketPriority(
                        id = priority.id,
                        name = name,
                        active = active,
                        note = note
                    )
                },
                onPost = {
                    showToast("TicketPriority Updated!")
                }
            )
        }
    }
}
