package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityPriorityUpdateBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.TicketPriority
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PriorityUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_PRIORITY = "intent_param_ticket_priority"

        fun start(context: Context, ticketPriority: TicketPriority) {

            val intent = Intent(context, PriorityUpdateActivity::class.java)
            intent.putExtra(PARAM_PRIORITY, ticketPriority)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val priority by lazy { intent.getParcelableExtra<TicketPriority>(PARAM_PRIORITY)!! }

    private lateinit var binding: ActivityPriorityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPriorityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.priorityName.setText(priority.name)
        binding.priorityActive.isChecked = priority.active
        binding.priorityNote.setText(priority.note)

        binding.bttnSubmit.setOnClickListener {

            val name = binding.priorityName.text.toString()
            val active = binding.priorityActive.isChecked
            val note = binding.priorityNote.text.toString()

            DoAsync(
                doWork = {

                    client.updateTicketPriority(
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
