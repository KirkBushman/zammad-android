package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.TicketPriority
import kotlinx.android.synthetic.main.activity_detail.*

class PriorityActivity : AppCompatActivity(R.layout.activity_detail) {

    companion object {

        private const val PARAM_PRIORITY = "intent_param_priority"

        fun start(context: Context, priority: TicketPriority) {

            val intent = Intent(context, PriorityActivity::class.java)
            intent.putExtra(PARAM_PRIORITY, priority)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val priority by lazy { intent.getParcelableExtra<TicketPriority>(PARAM_PRIORITY)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var newPriority: TicketPriority? = null
        doAsync(
            doWork = {
                newPriority = client?.ticketPriority(priority.id, true)
            },
            onPost = {
                model_text.text = newPriority.toString()
            }
        )
    }
}
