package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.TicketPriority
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PriorityActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_PRIORITY = "intent_param_priority"

        fun start(context: Context, priority: TicketPriority) {

            val intent = Intent(context, PriorityActivity::class.java)
            intent.putExtra(PARAM_PRIORITY, priority)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val priority by lazy { intent.getParcelableExtra<TicketPriority>(PARAM_PRIORITY)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newPriority: TicketPriority? = null
        DoAsync(
            doWork = {
                newPriority = client.ticketPriority(priority.id, true)
            },
            onPost = {
                binding.modelText.text = newPriority.toString()
            }
        )
    }
}
