package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.spinners.GroupSpinnerAdapter
import com.kirkbushman.sampleapp.spinners.UserSpinnerAdapter
import com.kirkbushman.sampleapp.spinners.PrioritySpinnerAdapter
import com.kirkbushman.sampleapp.spinners.StatesSpinnerAdapter
import com.kirkbushman.zammad.models.Group
import com.kirkbushman.zammad.models.TicketPriority
import com.kirkbushman.zammad.models.TicketState
import com.kirkbushman.zammad.models.User
import kotlinx.android.synthetic.main.activity_ticket_create.*

class TicketCreateActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {

            val intent = Intent(context, TicketCreateActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_create)

        val groups = ArrayList<Group>()
        doAsync(doWork = { groups.addAll(client?.groups() ?: listOf()) }, onPost = {

            bindGroupSpinner(groups)
        })

        val states = ArrayList<TicketState>()
        doAsync(doWork = { states.addAll(client?.ticketStates() ?: listOf()) }, onPost = {

            bindStateSpinner(states)
        })

        val priorities = ArrayList<TicketPriority>()
        doAsync(doWork = { priorities.addAll(client?.ticketPrioritites() ?: listOf()) }, onPost = {

            bindPrioritySpinner(priorities)
        })

        val users = ArrayList<User?>()
        doAsync(doWork = { users.addAll(client?.users() ?: listOf()) }, onPost = {

            bindOwnerSpinner(users)
            bindCustomerSpinner(users)
        })

        bttn_submit.setOnClickListener {

            val group = groups[ticket_group.selectedItemPosition]
            val state = states[ticket_state.selectedItemPosition]
            val priority = priorities[ticket_priority.selectedItemPosition]
            val owner = users[ticket_owner.selectedItemPosition]
            val customer = users[ticket_customer.selectedItemPosition]

            doAsync(doWork = {

                client?.createTicket(
                    title = ticket_title.text.trim().toString(),
                    groupId = group.id,
                    stateId = state.id,
                    priorityId = priority.id,
                    ownerId = owner?.id,
                    customerId = customer?.id,
                    subject = ticket_article_subject.text.trim().toString(),
                    body = ticket_article_body.text.trim().toString(),
                    type = "note",
                    internal = false
                )
            }, onPost = {

                Toast.makeText(this, "Ticket Created!", Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun bindGroupSpinner(groups: List<Group>) {
        val groupsAdapter = GroupSpinnerAdapter(this, R.layout.spinner_group_item, groups).apply {
            setDropDownViewResource(R.layout.spinner_group_item)
        }

        ticket_group.adapter = groupsAdapter
    }

    private fun bindStateSpinner(states: List<TicketState>) {
        val statesAdapter = StatesSpinnerAdapter(this, R.layout.spinner_state_item, states).apply {
            setDropDownViewResource(R.layout.spinner_state_item)
        }

        ticket_state.adapter = statesAdapter
    }

    private fun bindPrioritySpinner(priorities: List<TicketPriority>) {
        val prioritiesAdapter = PrioritySpinnerAdapter(this, R.layout.spinner_priority_item, priorities).apply {
            setDropDownViewResource(R.layout.spinner_priority_item)
        }

        ticket_priority.adapter = prioritiesAdapter
    }

    private fun bindOwnerSpinner(owners: List<User?>) {
        val ownersAdapter = UserSpinnerAdapter(this, R.layout.spinner_user_item, owners).apply {
            setDropDownViewResource(R.layout.spinner_user_item)
        }

        ticket_owner.adapter = ownersAdapter
    }

    private fun bindCustomerSpinner(customers: List<User?>) {
        val customersAdapter = UserSpinnerAdapter(this, R.layout.spinner_user_item, customers).apply {
            setDropDownViewResource(R.layout.spinner_user_item)
        }

        ticket_customer.adapter = customersAdapter
    }
}
