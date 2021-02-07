package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.databinding.ActivityTicketCreateBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.spinners.GroupSpinnerAdapter
import com.kirkbushman.sampleapp.spinners.UserSpinnerAdapter
import com.kirkbushman.sampleapp.spinners.PrioritySpinnerAdapter
import com.kirkbushman.sampleapp.spinners.StatesSpinnerAdapter
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Group
import com.kirkbushman.zammad.models.TicketPriority
import com.kirkbushman.zammad.models.TicketState
import com.kirkbushman.zammad.models.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TicketCreateActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {

            val intent = Intent(context, TicketCreateActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private lateinit var binding: ActivityTicketCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTicketCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val groups = ArrayList<Group>()
        DoAsync(
            doWork = {
                groups.addAll(client.groups() ?: listOf())
            },
            onPost = {
                bindGroupSpinner(groups)
            }
        )

        val states = ArrayList<TicketState>()
        DoAsync(
            doWork = {
                states.addAll(client.ticketStates() ?: listOf())
            },
            onPost = {
                bindStateSpinner(states)
            }
        )

        val priorities = ArrayList<TicketPriority>()
        DoAsync(
            doWork = {
                priorities.addAll(client.ticketPrioritites() ?: listOf())
            },
            onPost = {
                bindPrioritySpinner(priorities)
            }
        )

        val users = ArrayList<User?>()
        DoAsync(
            doWork = {
                users.addAll(client.users() ?: listOf())
            },
            onPost = {

                bindOwnerSpinner(users)
                bindCustomerSpinner(users)
            }
        )

        binding.bttnSubmit.setOnClickListener {

            val group = groups[binding.ticketGroup.selectedItemPosition]
            val state = states[binding.ticketState.selectedItemPosition]
            val priority = priorities[binding.ticketPriority.selectedItemPosition]
            val owner = users[binding.ticketOwner.selectedItemPosition]
            val customer = users[binding.ticketCustomer.selectedItemPosition]

            DoAsync(
                doWork = {

                    client.createTicket(
                        title = binding.ticketTitle.text.trim().toString(),
                        groupId = group.id,
                        stateId = state.id,
                        priorityId = priority.id,
                        ownerId = owner?.id,
                        customerId = customer?.id,
                        subject = binding.ticketArticleSubject.text.trim().toString(),
                        body = binding.ticketArticleBody.text.trim().toString(),
                        type = "note",
                        internal = false
                    )
                },
                onPost = {
                    Toast.makeText(this, "Ticket Created!", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun bindGroupSpinner(groups: List<Group>) {
        val groupsAdapter = GroupSpinnerAdapter(this, R.layout.spinner_group_item, groups).apply {
            setDropDownViewResource(R.layout.spinner_group_item)
        }

        binding.ticketGroup.adapter = groupsAdapter
    }

    private fun bindStateSpinner(states: List<TicketState>) {
        val statesAdapter = StatesSpinnerAdapter(this, R.layout.spinner_state_item, states).apply {
            setDropDownViewResource(R.layout.spinner_state_item)
        }

        binding.ticketState.adapter = statesAdapter
    }

    private fun bindPrioritySpinner(priorities: List<TicketPriority>) {
        val prioritiesAdapter = PrioritySpinnerAdapter(this, R.layout.spinner_priority_item, priorities).apply {
            setDropDownViewResource(R.layout.spinner_priority_item)
        }

        binding.ticketPriority.adapter = prioritiesAdapter
    }

    private fun bindOwnerSpinner(owners: List<User?>) {
        val ownersAdapter = UserSpinnerAdapter(this, R.layout.spinner_user_item, owners).apply {
            setDropDownViewResource(R.layout.spinner_user_item)
        }

        binding.ticketOwner.adapter = ownersAdapter
    }

    private fun bindCustomerSpinner(customers: List<User?>) {
        val customersAdapter = UserSpinnerAdapter(this, R.layout.spinner_user_item, customers).apply {
            setDropDownViewResource(R.layout.spinner_user_item)
        }

        binding.ticketCustomer.adapter = customersAdapter
    }
}
