package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityStateUpdateBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.TicketState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StateUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_STATE = "intent_param_state"

        fun start(context: Context, state: TicketState) {

            val intent = Intent(context, StateUpdateActivity::class.java)
            intent.putExtra(PARAM_STATE, state)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val state by lazy { intent.getParcelableExtra<TicketState>(PARAM_STATE)!! }

    private lateinit var binding: ActivityStateUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStateUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stateName.setText(state.name)
        binding.stateActive.isChecked = state.active
        binding.stateNote.setText(state.note)

        binding.bttnSubmit.setOnClickListener {

            val name = binding.stateName.text.toString()
            val active = binding.stateActive.isChecked
            val note = binding.stateNote.text.toString()

            DoAsync(
                doWork = {

                    client.updateTicketState(
                        id = state.id,
                        name = name,
                        active = active,
                        note = note
                    )
                },
                onPost = {
                    showToast("TicketState Updated!")
                }
            )
        }
    }
}
