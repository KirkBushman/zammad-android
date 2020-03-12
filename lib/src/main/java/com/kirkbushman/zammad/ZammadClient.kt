package com.kirkbushman.zammad

import android.util.Base64
import android.util.Log
import com.kirkbushman.zammad.models.*
import com.kirkbushman.zammad.models.compat.TicketArticleCompat
import com.kirkbushman.zammad.models.compat.TicketCompat
import com.kirkbushman.zammad.utils.Utils.buildRetrofit
import retrofit2.Retrofit

class ZammadClient(

    baseUrl: String,

    private val auth: String,
    private val logging: Boolean

) {

    constructor(baseUrl: String, username: String, password: String, logging: Boolean) : this(baseUrl, "$username:$password", logging)

    companion object {

        @Volatile
        private var retrofit: Retrofit? = null
        @Volatile
        private var api: ZammadApi? = null

        @Synchronized
        fun getRetrofit(baseUrl: String, logging: Boolean): Retrofit {
            synchronized(this) {

                if (retrofit == null) {
                    retrofit = buildRetrofit(baseUrl, logging)
                }

                return retrofit!!
            }
        }

        @Synchronized
        fun getRetrofit(): Retrofit {
            synchronized(this) {

                if (retrofit == null) {
                    throw IllegalStateException("Retrofit instance is null, have you called `getRetrofit(baseUrl, logging)`?")
                }

                return retrofit!!
            }
        }

        @Synchronized
        fun getApi(baseUrl: String, logging: Boolean): ZammadApi {
            synchronized(this) {

                if (api == null) {
                    api = getRetrofit(baseUrl, logging).create(ZammadApi::class.java)
                }

                return api!!
            }
        }

        @Synchronized
        fun getApi(): ZammadApi {
            synchronized(this) {

                if (api == null) {

                    if (retrofit == null) {
                        throw IllegalStateException("Retrofit instance is null, have you called `getRetrofit(baseUrl, logging)`?")
                    }

                    api = getRetrofit().create(ZammadApi::class.java)
                }

                return api!!
            }
        }

        fun me(baseUrl: String, username: String, password: String, logging: Boolean): User? {

            val auth = "$username:$password"
            val authMap = hashMapOf("Authorization" to "Basic ".plus(String(Base64.encode(auth.toByteArray(), Base64.NO_WRAP))))
            val api = getApi(baseUrl, logging)
            val req = api.me(authMap)
            val res = req.execute()

            if (!res.isSuccessful) {
                return null
            }

            return res.body()
        }
    }

    private val api = getApi(baseUrl, logging)

    fun me(expanded: Boolean = false): User? {

        val authMap = getHeaderMap()
        val req = api.me(expanded, authMap)
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

    fun updateUser(id: Int, user: User): User? {

        val authMap = getHeaderMap()
        val req = api.updateUser(id, user, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun updateUser(

        id: Int,

        organizationId: Int? = null,
        organization: String? = null,
        active: Boolean? = null,
        login: String? = null,
        firstname: String? = null,
        lastname: String? = null,
        email: String? = null,
        image: String? = null,
        imageSource: String? = null,
        web: String? = null,
        phone: String? = null,
        fax: String? = null,
        mobile: String? = null,
        department: String? = null,
        street: String? = null,
        zip: String? = null,
        city: String? = null,
        country: String? = null,
        address: String? = null,
        isVip: Boolean? = null,
        isVerified: Boolean? = null,
        note: String? = null,
        source: String? = null,
        lastLogin: String? = null,
        loginFailed: Int? = null,
        outOfOffice: Boolean? = null,
        outOfOfficeStartAt: String? = null,
        outOfOfficeEndAt: String? = null,
        outOfOfficeReplacementId: Int? = null
    ): User? {

        val authMap = getHeaderMap()
        val req = api.updateUser(
            id = id,
            organizationId = organizationId,
            organization = organization,
            active = active,
            login = login,
            firstname = firstname,
            lastname = lastname,
            email = email,
            image = image,
            imageSource = imageSource,
            web = web,
            phone = phone,
            fax = fax,
            mobile = mobile,
            department = department,
            street = street,
            zip = zip,
            city = city,
            country = country,
            address = address,
            isVip = isVip,
            isVerified = isVerified,
            note = note,
            source = source,
            lastLogin = lastLogin,
            loginFailed = loginFailed,
            outOfOffice = outOfOffice,
            outOfOfficeStartAt = outOfOfficeStartAt,
            outOfOfficeEndAt = outOfOfficeEndAt,
            outOfOfficeReplacementId = outOfOfficeReplacementId,
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

    fun updateOrganization(id: Int, organization: Organization): Organization? {

        val authMap = getHeaderMap()
        val req = api.updateOrganization(id, organization, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun updateOrganization(

        id: Int,

        name: String? = null,
        shared: Boolean? = null,
        domain: Boolean? = null,
        domainAssignment: Boolean? = null,
        active: Boolean? = null,
        note: String? = null
    ): Organization? {

        val authMap = getHeaderMap()
        val req = api.updateOrganization(
            id = id,
            name = name,
            shared = shared,
            domain = domain,
            domainAssignment = domainAssignment,
            active = active,
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

    fun updateGroup(id: Int, group: Group): Group? {

        val authMap = getHeaderMap()
        val req = api.updateGroup(id, group, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun updateGroup(

        id: Int,

        name: String? = null,
        signatureId: Int? = null,
        emailAddressId: Int? = null,
        assignmentTimeout: Int? = null,
        followUpPossible: String? = null,
        followUpAssignment: Boolean? = null,
        active: Boolean? = null,
        note: String? = null
    ): Group? {

        val authMap = getHeaderMap()
        val req = api.updateGroup(
            id = id,
            name = name,
            signatureId = signatureId,
            emailAddressId = emailAddressId,
            assignmentTimeout = assignmentTimeout,
            followUpPossible = followUpPossible,
            followUpAssignment = followUpAssignment,
            active = active,
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

    fun updateTicketState(id: Int, ticketState: TicketState): TicketState? {

        val authMap = getHeaderMap()
        val req = api.updateTicketState(id, ticketState, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun updateTicketState(

        id: Int,

        name: String? = null,
        active: Boolean? = null,
        ignoreEscalation: Boolean? = null,
        defaultCreate: Boolean? = null,
        defaultFollowUp: Boolean? = null,
        note: String? = null
    ): TicketState? {

        val authMap = getHeaderMap()
        val req = api.updateTicketState(
            id = id,
            name = name,
            active = active,
            ignoreEscalation = ignoreEscalation,
            defaultCreate = defaultCreate,
            defaultFollowUp = defaultFollowUp,
            note = note,
            header = authMap
        )

        val res = req.execute()
        if (res.isSuccessful) {

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

    fun updateTicketPriority(id: Int, ticketPriority: TicketPriority): TicketPriority? {

        val authMap = getHeaderMap()
        val req = api.updateTicketPriority(id, ticketPriority, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return null
    }

    fun updateTicketPriority(

        id: Int,

        name: String? = null,
        active: Boolean? = null,
        note: String? = null
    ): TicketPriority? {

        val authMap = getHeaderMap()
        val req = api.updateTicketPriority(
            id = id,
            name = name,
            active = active,
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

    fun updateTicket(id: Int, ticket: Ticket): Ticket? {

        val authMap = getHeaderMap()
        val req = api.updateTicket(id, ticket, authMap)
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

    fun ticketArticleAttachment(ticket: Ticket, ticketArticle: TicketArticle, articleAttachment: ArticleAttachment): String? {

        return ticketArticleAttachment(ticket.id, ticketArticle.id, articleAttachment.id)
    }

    fun ticketArticleAttachment(ticketId: Int, articleId: Int, attachmentId: Int): String? {

        val authMap = getHeaderMap()
        val req = api.ticketArticleAttachment(
            ticketId = ticketId,
            articleId = articleId,
            attachmentId = attachmentId,
            header = authMap
        )

        val res = req.execute()

        if (!res.isSuccessful) {
            return null
        }

        return res.body()?.string()
    }

    fun objects(): List<Object>? {

        val authMap = getHeaderMap()
        val req = api.objects(authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun `object`(id: Int): Object? {

        val authMap = getHeaderMap()
        val req = api.`object`(id, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun onlineNotifications(expanded: Boolean = false): List<OnlineNotification>? {

        val authMap = getHeaderMap()
        val req = api.onlineNotifications(expanded, authMap)
        val res = req.execute()

        if (!res.isSuccessful) {

            if (logging) {
                Log.i("Retrofit Error", res.errorBody().toString())
            }

            return null
        }

        return res.body()
    }

    fun onlineNotification(id: Int, expanded: Boolean = false): OnlineNotification? {

        val authMap = getHeaderMap()
        val req = api.onlineNotification(id, expanded, authMap)
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
