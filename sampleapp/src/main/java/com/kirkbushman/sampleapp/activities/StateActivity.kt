package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.TicketState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_STATE = "intent_param_state"

        fun start(context: Context, state: TicketState) {

            val intent = Intent(context, StateActivity::class.java)
            intent.putExtra(PARAM_STATE, state)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val state by lazy { intent.getParcelableExtra<TicketState>(PARAM_STATE)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newState: TicketState? = null
        DoAsync(
            doWork = {
                newState = client.ticketState(state.id, true)
            },
            onPost = {
                binding.modelText.text = newState.toString()
            }
        )
    }
}
