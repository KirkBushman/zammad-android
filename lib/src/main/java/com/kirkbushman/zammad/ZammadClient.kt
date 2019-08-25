package com.kirkbushman.zammad

import android.util.Base64
import android.util.Log
import com.kirkbushman.zammad.models.*
import com.kirkbushman.zammad.models.compat.TicketArticleCompat
import com.kirkbushman.zammad.models.compat.TicketCompat
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ZammadClient(

    baseUrl: String,

    private val auth: String,
    private val logging: Boolean

) {

    constructor(baseUrl: String, username: String, password: String, logging: Boolean) : this(baseUrl, "$username:$password", logging)

    companion object {

        fun me(baseUrl: String, username: String, password: String, logging: Boolean): User? {

            val auth = "$username:$password"
            val authMap = hashMapOf("Authorization" to "Basic ".plus(String(Base64.encode(auth.toByteArray(), Base64.NO_WRAP))))
            val retrofit = getRetrofit(baseUrl, logging)
            val api = retrofit.create(ZammadApi::class.java)
            val req = api.me(authMap)
            val res = req.execute()

            if (!res.isSuccessful) {
                return null
            }

            return res.body()
        }

        private fun getRetrofit(baseUrl: String, logging: Boolean): Retrofit {

            val moshi = Moshi.Builder().build()
            val moshiFactory = MoshiConverterFactory.create(moshi)

            val httpClient = if (logging) {

                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            } else {

                OkHttpClient.Builder()
                    .build()
            }

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(moshiFactory)
                .client(httpClient)
                .build()
        }
    }

    private val retrofit = getRetrofit(baseUrl, logging)
    private val api = retrofit.create(ZammadApi::class.java)

    fun me(): User? {

        val authMap = getHeaderMap()
        val req = api.me(authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error [me]", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun users(expanded: Boolean = false): List<User>? {

        val authMap = getHeaderMap()
        val req = api.users(expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error [users]", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun user(id: Int, expanded: Boolean = false): User? {

        val authMap = getHeaderMap()
        val req = api.user(id, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error [user]", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun searchUsers(query: String, limit: Int? = null, expanded: Boolean = false): List<User>? {

        val authMap = getHeaderMap()
        val req = api.searchUsers(query, limit, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun deleteUser(user: User): Boolean {

        return deleteUser(user.id)
    }

    fun deleteUser(id: Int): Boolean {

        val authMap = getHeaderMap()
        val req = api.deleteUser(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return false
        }

        return true
    }

    fun organizations(expanded: Boolean = false): List<Organization>? {

        val authMap = getHeaderMap()
        val req = api.organizations(expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun organization(id: Int, expanded: Boolean = false): Organization? {

        val authMap = getHeaderMap()
        val req = api.organization(id, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun searchOrganizations(query: String, limit: Int? = null, expanded: Boolean = false): List<Organization>? {

        val authMap = getHeaderMap()
        val req = api.searchOrganizations(query, limit, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun deleteOrganization(organization: Organization): Boolean {

        return deleteOrganization(organization.id)
    }

    fun deleteOrganization(id: Int): Boolean {

        val authMap = getHeaderMap()
        val req = api.deleteOrganization(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return false
        }

        return true
    }

    fun overviews(expanded: Boolean = false): List<Overview>? {

        val authMap = getHeaderMap()
        val req = api.overviews(expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun overview(id: Int, expanded: Boolean = false): Overview? {

        val authMap = getHeaderMap()
        val req = api.overview(id, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun groups(expanded: Boolean = false): List<Group>? {

        val authMap = getHeaderMap()
        val req = api.groups(expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error [groups]", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun group(id: Int, expanded: Boolean = false): Group? {

        val authMap = getHeaderMap()
        val req = api.group(id, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error [group]", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun deleteGroup(group: Group): Boolean {

        return deleteGroup(group.id)
    }

    fun deleteGroup(id: Int): Boolean {

        val authMap = getHeaderMap()
        val req = api.deleteGroup(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return false
        }

        return true
    }

    fun roles(expanded: Boolean = false): List<Role>? {

        val authMap = getHeaderMap()
        val req = api.roles(expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error [roles]", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun role(id: Int, expanded: Boolean = false): Role? {

        val authMap = getHeaderMap()
        val req = api.role(id, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error [role]", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun tags(): List<Tag>? {

        val authMap = getHeaderMap()
        val req = api.tags(authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error [tags]", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun ticketStates(expanded: Boolean = false): List<TicketState>? {

        val authMap = getHeaderMap()
        val req = api.ticketStates(expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun ticketState(id: Int, expanded: Boolean = false): TicketState? {

        val authMap = getHeaderMap()
        val req = api.ticketState(id, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun deleteTicketState(ticketState: TicketState): Boolean {

        return deleteTicketState(ticketState.id)
    }

    fun deleteTicketState(id: Int): Boolean {

        val authMap = getHeaderMap()
        val req = api.deleteTicketState(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return false
        }

        return true
    }

    fun ticketPrioritites(expanded: Boolean = false): List<TicketPriority>? {

        val authMap = getHeaderMap()
        val req = api.ticketPriorities(expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun ticketPriority(id: Int, expanded: Boolean = false): TicketPriority? {

        val authMap = getHeaderMap()
        val req = api.ticketPriority(id, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun deleteTicketPriority(ticketPriority: TicketPriority): Boolean {

        return deleteTicketPriority(ticketPriority.id)
    }

    fun deleteTicketPriority(id: Int): Boolean {

        val authMap = getHeaderMap()
        val req = api.deleteTicketPriority(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return false
        }

        return true
    }

    fun tickets(expanded: Boolean = false): List<Ticket>? {

        val authMap = getHeaderMap()
        val req = api.tickets(expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun ticket(id: Int, expanded: Boolean = false): Ticket? {

        val authMap = getHeaderMap()
        val req = api.ticket(id, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun createTicket(

        title: String,
        groupId: Int? = null,
        group: String? = null,
        stateId: Int? = null,
        state: String? = null,
        priorityId: Int? = null,
        priority: String? = null,
        customerId: Int? = null,
        customer: String? = null,
        ownerId: Int? = null,
        owner: String? = null,
        subject: String,
        body: String,
        type: String,
        internal: Boolean,
        note: String? = null

    ): Ticket? {

        val authMap = getHeaderMap()
        val req = api.createTicket(
            TicketCompat(
                title = title,
                groupId = groupId,
                group = group,
                stateId = stateId,
                state = state,
                priorityId = priorityId,
                priority = priority,
                customerId = customerId,
                customer = customer,
                ownerId = ownerId,
                owner = owner,
                article = TicketArticleCompat(
                    subject = subject,
                    body = body,
                    type = type,
                    internal = internal
                ),
                note = note
            ),
            header = authMap
        )

        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun updateTicket(
        id: Int,

        title: String? = null,
        groupId: Int? = null,
        group: String? = null,
        stateId: Int? = null,
        state: String? = null,
        priorityId: Int? = null,
        priority: String? = null,
        ownerId: Int? = null,
        owner: String? = null,
        customerId: Int? = null,
        customer: String? = null,
        note: String? = null
    ): Ticket? {

        val authMap = getHeaderMap()
        val req = api.updateTicket(
            id = id,
            title = title,
            groupId = groupId,
            group = group,
            stateId = stateId,
            state = state,
            priorityId = priorityId,
            priority = priority,
            ownerId = ownerId,
            owner = owner,
            customerId = customerId,
            customer = customer,
            note = note,
            header = authMap
        )

        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun deleteTicket(ticket: Ticket): Boolean {

        return deleteTicket(ticket.id)
    }

    fun deleteTicket(id: Int): Boolean {

        val authMap = getHeaderMap()
        val req = api.deleteTicket(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return false
        }

        return true
    }

    fun searchTickets(query: String, page: Int, perPage: Int, expanded: Boolean = false): SearchResult? {

        val authMap = getHeaderMap()
        val req = api.searchTickets(query, page, perPage, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun ticketArticles(ticket: Ticket): List<TicketArticle>? {

        return ticketArticles(ticket.id)
    }

    fun ticketArticles(ticketId: Int, expanded: Boolean = false): List<TicketArticle>? {

        val authMap = getHeaderMap()
        val req = api.ticketArticles(ticketId, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun ticketArticle(id: Int, expanded: Boolean = false): TicketArticle? {

        val authMap = getHeaderMap()
        val req = api.ticketArticle(id, expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun createTicketArticle(

        ticketId: Int,

        to: String? = "",
        cc: String? = "",
        subject: String,
        body: String,
        contentType: String,
        type: String,
        internal: Boolean,
        timeUnit: String

    ): TicketArticle? {

        val authMap = getHeaderMap()
        val req = api.createTicketArticle(
            ticketId = ticketId,
            to = to,
            cc = cc,
            subject = subject,
            body = body,
            contentType = contentType,
            type = type,
            internal = internal,
            timeUnit = timeUnit,
            header = authMap
        )

        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun onlineNotifications(): List<OnlineNotification>? {

        val authMap = getHeaderMap()
        val req = api.onlineNotifications(authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun onlineNotification(id: Int): OnlineNotification? {

        val authMap = getHeaderMap()
        val req = api.onlineNotification(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {
            return null
        }

        return res.body()
    }

    fun updateOnlineNotification(id: Int, seen: Boolean): OnlineNotification? {

        val authMap = getHeaderMap()
        val req = api.updateOnlineNotification(id, seen, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun deleteOnlineNotification(id: Int): Boolean {

        val authMap = getHeaderMap()
        val req = api.deleteOnlineNotification(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return false
        }

        return true
    }

    fun markAllOnlineNotificationsAsRead(): Any? {

        val authMap = getHeaderMap()
        val req = api.markAllOnlineNotificationsAsRead(authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    private fun getHeaderMap(): HashMap<String, String> {
        return hashMapOf("Authorization" to "Basic ".plus(String(Base64.encode(auth.toByteArray(), Base64.NO_WRAP))))
    }
}
