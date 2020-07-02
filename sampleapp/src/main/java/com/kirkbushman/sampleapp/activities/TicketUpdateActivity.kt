package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.showToast
import com.kirkbushman.sampleapp.spinners.GroupSpinnerAdapter
import com.kirkbushman.sampleapp.spinners.UserSpinnerAdapter
import com.kirkbushman.sampleapp.spinners.PrioritySpinnerAdapter
import com.kirkbushman.sampleapp.spinners.StatesSpinnerAdapter
import com.kirkbushman.zammad.models.*
import kotlinx.android.synthetic.main.activity_ticket_update.*

class TicketUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_TICKET = "intent_param_ticket"

        fun start(context: Context, ticket: Ticket) {

            val intent = Intent(context, TicketUpdateActivity::class.java)
            intent.putExtra(PARAM_TICKET, ticket)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val ticket by lazy { intent.getParcelableExtra(PARAM_TICKET) as Ticket }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_update)

        val groups = ArrayList<Group>()
        doAsync(
            doWork = {
                groups.addAll(client?.groups() ?: listOf())
            },
            onPost = {
                bindGroupSpinner(groups)
            }
        )

        val states = ArrayList<TicketState>()
        doAsync(
            doWork = {
                states.addAll(client?.ticketStates() ?: listOf())
            },
            onPost = {
                bindStateSpinner(states)
            }
        )

        val priorities = ArrayList<TicketPriority>()
        doAsync(
            doWork = {
                priorities.addAll(client?.ticketPrioritites() ?: listOf())
            },
            onPost = {
                bindPrioritySpinner(priorities)
            }
        )

        val users = ArrayList<User?>()
        doAsync(
            doWork = {
                users.addAll(client?.users() ?: listOf())
            },
            onPost = {

                bindOwnerSpinner(users)
                bindCustomersSpinner(users)
            }
        )

        ticket_title.setText(ticket.title)

        bttn_submit.setOnClickListener {

            val group = groups[ticket_group.selectedItemPosition]
            val state = states[ticket_state.selectedItemPosition]
            val priority = priorities[ticket_priority.selectedItemPosition]
            val owner = users[ticket_owner.selectedItemPosition]
            val customer = users[ticket_customer.selectedItemPosition]

            doAsync(
                doWork = {

                    client?.updateTicket(
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

        ticket_group.adapter = groupsAdapter

        val selectedGroup = groups.find { it.id == ticket.groupId }
        if (selectedGroup != null) {

            ticket_group.setSelection(groups.indexOf(selectedGroup))
        }
    }

    private fun bindStateSpinner(states: List<TicketState>) {
        val statesAdapter = StatesSpinnerAdapter(this, R.layout.spinner_state_item, states).apply {
            setDropDownViewResource(R.layout.spinner_state_item)
        }

        ticket_state.adapter = statesAdapter

        val selectedState = states.find { it.id == ticket.stateId }
        if (selectedState != null) {

            ticket_state.setSelection(states.indexOf(selectedState))
        }
    }

    private fun bindPrioritySpinner(priorities: List<TicketPriority>) {
        val prioritiesAdapter = PrioritySpinnerAdapter(this, R.layout.spinner_priority_item, priorities).apply {
            setDropDownViewResource(R.layout.spinner_priority_item)
        }

        ticket_priority.adapter = prioritiesAdapter

        val selectedPriority = priorities.find { it.id == ticket.priorityId }
        if (selectedPriority != null) {

            ticket_priority.setSelection(priorities.indexOf(selectedPriority))
        }
    }

    private fun bindOwnerSpinner(owners: List<User?>) {
        val ownersAdapter = UserSpinnerAdapter(this, R.layout.spinner_user_item, owners).apply {
            setDropDownViewResource(R.layout.spinner_user_item)
        }

        ticket_owner.adapter = ownersAdapter

        val selectedOwner = owners.find { it?.id == ticket.ownerId }
        if (selectedOwner != null) {

            ticket_owner.setSelection(owners.indexOf(selectedOwner))
        }
    }

    private fun bindCustomersSpinner(customers: List<User?>) {
        val customersAdapter = UserSpinnerAdapter(this, R.layout.spinner_user_item, customers).apply {
            setDropDownViewResource(R.layout.spinner_user_item)
        }

        ticket_customer.adapter = customersAdapter

        val selectedCustomer = customers.find { it?.id == ticket.ownerId }
        if (selectedCustomer != null) {

            ticket_customer.setSelection(customers.indexOf(selectedCustomer))
        }
    }
}
