package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.databinding.ActivityTicketUpdateBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.sampleapp.spinners.GroupSpinnerAdapter
import com.kirkbushman.sampleapp.spinners.UserSpinnerAdapter
import com.kirkbushman.sampleapp.spinners.PrioritySpinnerAdapter
import com.kirkbushman.sampleapp.spinners.StatesSpinnerAdapter
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TicketUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_TICKET = "intent_param_ticket"

        fun start(context: Context, ticket: Ticket) {

            val intent = Intent(context, TicketUpdateActivity::class.java)
            intent.putExtra(PARAM_TICKET, ticket)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val ticket by lazy { intent.getParcelableExtra<Ticket>(PARAM_TICKET)!! }

    private lateinit var binding: ActivityTicketUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTicketUpdateBinding.inflate(layoutInflater)
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
                bindCustomersSpinner(users)
            }
        )

        binding.ticketTitle.setText(ticket.title)

        binding.bttnSubmit.setOnClickListener {

            val group = groups[binding.ticketGroup.selectedItemPosition]
            val state = states[binding.ticketState.selectedItemPosition]
            val priority = priorities[binding.ticketPriority.selectedItemPosition]
            val owner = users[binding.ticketOwner.selectedItemPosition]
            val customer = users[binding.ticketCustomer.selectedItemPosition]

            DoAsync(
                doWork = {

                    client.updateTicket(
                        id = ticket.id,
                        groupId = group.id,
                        stateId = state.id,
                        priorityId = priority.id,
                        ownerId = owner?.id,
                        customerId = customer?.id
                    )
                },
                onPost = {
                    showToast("Ticket Updated!")
                }
            )
        }
    }

    private fun bindGroupSpinner(groups: List<Group>) {
        val groupsAdapter = GroupSpinnerAdapter(this, R.layout.spinner_group_item, groups).apply {
            setDropDownViewResource(R.layout.spinner_group_item)
        }

        binding.ticketGroup.adapter = groupsAdapter

        val selectedGroup = groups.find { it.id == ticket.groupId }
        if (selectedGroup != null) {

            binding.ticketGroup.setSelection(groups.indexOf(selectedGroup))
        }
    }

    private fun bindStateSpinner(states: List<TicketState>) {
        val statesAdapter = StatesSpinnerAdapter(this, R.layout.spinner_state_item, states).apply {
            setDropDownViewResource(R.layout.spinner_state_item)
        }

        binding.ticketState.adapter = statesAdapter

        val selectedState = states.find { it.id == ticket.stateId }
        if (selectedState != null) {

            binding.ticketState.setSelection(states.indexOf(selectedState))
        }
    }

    private fun bindPrioritySpinner(priorities: List<TicketPriority>) {
        val prioritiesAdapter = PrioritySpinnerAdapter(this, R.layout.spinner_priority_item, priorities).apply {
            setDropDownViewResource(R.layout.spinner_priority_item)
        }

        binding.ticketPriority.adapter = prioritiesAdapter

        val selectedPriority = priorities.find { it.id == ticket.priorityId }
        if (selectedPriority != null) {

            binding.ticketPriority.setSelection(priorities.indexOf(selectedPriority))
        }
    }

    private fun bindOwnerSpinner(owners: List<User?>) {
        val ownersAdapter = UserSpinnerAdapter(this, R.layout.spinner_user_item, owners).apply {
            setDropDownViewResource(R.layout.spinner_user_item)
        }

        binding.ticketOwner.adapter = ownersAdapter

        val selectedOwner = owners.find { it?.id == ticket.ownerId }
        if (selectedOwner != null) {

            binding.ticketOwner.setSelection(owners.indexOf(selectedOwner))
        }
    }

    private fun bindCustomersSpinner(customers: List<User?>) {
        val customersAdapter = UserSpinnerAdapter(this, R.layout.spinner_user_item, customers).apply {
            setDropDownViewResource(R.layout.spinner_user_item)
        }

        binding.ticketCustomer.adapter = customersAdapter

        val selectedCustomer = customers.find { it?.id == ticket.ownerId }
        if (selectedCustomer != null) {

            binding.ticketCustomer.setSelection(customers.indexOf(selectedCustomer))
        }
    }
}
