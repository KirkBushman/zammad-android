package com.kirkbushman.zammad

import com.kirkbushman.zammad.models.*
import com.kirkbushman.zammad.models.Tag
import com.kirkbushman.zammad.models.compat.TicketCompat
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ZammadApi {

    @GET("/api/v1/users/me")
    fun me(
        @HeaderMap header: HashMap<String, String>
    ): Call<User>

    @GET("/api/v1/users")
    fun users(
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<User>>

    @GET("/api/v1/users/{id}")
    fun user(
        @Path("id") id: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<User>

    @FormUrlEncoded
    @PUT("/api/v1/users/{id}")
    fun updateUser(
        @Path("id") id: Int,
        @Body body: User,
        @HeaderMap header: HashMap<String, String>
    ): Call<User>

    @FormUrlEncoded
    @PUT("/api/v1/users/{id}")
    fun updateUser(
        @Path("id") id: Int,

        @Field("organization_id") organizationId: Int? = null,
        @Field("organization") organization: String? = null,
        @Field("active") active: Boolean? = null,
        @Field("login") login: String? = null,
        @Field("firstname") firstname: String? = null,
        @Field("lastname") lastname: String? = null,
        @Field("email") email: String? = null,
        @Field("image") image: String? = null,
        @Field("image_source") imageSource: String? = null,
        @Field("web") web: String? = null,
        @Field("phone") phone: String? = null,
        @Field("fax") fax: String? = null,
        @Field("mobile") mobile: String? = null,
        @Field("department") department: String? = null,
        @Field("street") street: String? = null,
        @Field("zip") zip: String? = null,
        @Field("city") city: String? = null,
        @Field("country") country: String? = null,
        @Field("address") address: String? = null,
        @Field("vip") isVip: Boolean? = null,
        @Field("verified") isVerified: Boolean? = null,
        @Field("note") note: String? = null,
        @Field("source") source: String? = null,
        @Field("last_login") lastLogin: String? = null,
        @Field("login_failed") loginFailed: Int? = null,
        @Field("out_of_office") outOfOffice: Boolean? = null,
        @Field("out_of_office_start_at") outOfOfficeStartAt: String? = null,
        @Field("out_of_office_end_at") outOfOfficeEndAt: String? = null,
        @Field("out_of_office_replacement_id") outOfOfficeReplacementId: Int? = null,

        @HeaderMap header: HashMap<String, String>
    ): Call<User>

    @DELETE("/api/v1/users/{id}")
    fun deleteUser(
        @Path("id") id: Int,
        @HeaderMap header: HashMap<String, String>
    ): Call<ResponseBody>

    @GET("/api/v1/users/search")
    fun searchUsers(
        @Query("query") query: String,
        @Query("limit") limit: Int?,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<User>>

    @GET("/api/v1/organizations")
    fun organizations(
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<Organization>>

    @GET("/api/v1/organizations/{id}")
    fun organization(
        @Path("id") id: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<Organization>

    @FormUrlEncoded
    @PUT("/api/v1/organizations/{id}")
    fun updateOrganization(
        @Path("id") id: Int,
        @Body body: Organization,
        @HeaderMap header: HashMap<String, String>
    ): Call<Organization>

    @FormUrlEncoded
    @PUT("/api/v1/organizations/{id}")
    fun updateOrganization(
        @Path("id") id: Int,

        @Field("name") name: String? = null,
        @Field("shared") shared: Boolean? = null,
        @Field("domain") domain: Boolean? = null,
        @Field("domain_assignment") domainAssignment: Boolean? = null,
        @Field("active") active: Boolean? = null,
        @Field("note") note: String? = null,

        @HeaderMap header: HashMap<String, String>
    ): Call<Organization>

    @DELETE("/api/v1/organization/{id}")
    fun deleteOrganization(
        @Path("id") id: Int,
        @HeaderMap header: HashMap<String, String>
    ): Call<ResponseBody>

    @GET("/api/v1/organizations/search")
    fun searchOrganizations(
        @Query("query") query: String,
        @Query("limit") limit: Int?,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<Organization>>

    @GET("/api/v1/overviews")
    fun overviews(
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<Overview>>

    @GET("/api/v1/overviews/{id}")
    fun overview(
        @Path("id") id: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<Overview>

    @GET("/api/v1/groups")
    fun groups(
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<Group>>

    @GET("/api/v1/groups/{id}")
    fun group(
        @Path("id") id: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<Group>

    @FormUrlEncoded
    @PUT("/api/v1/groups/{id}")
    fun updateGroup(
        @Path("id") id: Int,
        @Body body: Group,
        @HeaderMap header: HashMap<String, String>
    ): Call<Group>

    @FormUrlEncoded
    @PUT("/api/v1/groups/{id}")
    fun updateGroup(
        @Path("id") id: Int,

        @Field("name") name: String? = null,
        @Field("signature_id") signatureId: Int? = null,
        @Field("email_address_id") emailAddressId: Int? = null,
        @Field("assignment_timeout") assignmentTimeout: Int? = null,
        @Field("follow_up_possible") followUpPossible: String? = null,
        @Field("follow_up_assignment") followUpAssignment: Boolean? = null,
        @Field("active") active: Boolean? = null,
        @Field("note") note: String? = null,

        @HeaderMap header: HashMap<String, String>
    ): Call<Group>

    @DELETE("/api/v1/groups/{id}")
    fun deleteGroup(
        @Path("id") id: Int,
        @HeaderMap header: HashMap<String, String>
    ): Call<ResponseBody>

    @GET("/api/v1/roles")
    fun roles(
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<Role>>

    @GET("/api/v1/roles/{id}")
    fun role(
        @Path("id") id: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<Role>

    @GET("/api/v1/tag_list")
    fun tags(
        @HeaderMap header: HashMap<String, String>
    ): Call<List<Tag>>

    @GET("/api/v1/ticket_states")
    fun ticketStates(
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<TicketState>>

    @GET("/api/v1/ticket_states/{id}")
    fun ticketState(
        @Path("id") id: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<TicketState>

    @FormUrlEncoded
    @PUT("/api/v1/ticket_states/{id}")
    fun updateTicketState(
        @Path("id") id: Int,
        @Body body: TicketState,
        @HeaderMap header: HashMap<String, String>
    ): Call<TicketState>

    @FormUrlEncoded
    @PUT("/api/v1/ticket_states/{id}")
    fun updateTicketState(
        @Path("id") id: Int,

        @Field("name") name: String? = null,
        @Field("active") active: Boolean? = null,
        @Field("ignore_escalation") ignoreEscalation: Boolean? = null,
        @Field("default_create") defaultCreate: Boolean? = null,
        @Field("default_follow_up") defaultFollowUp: Boolean? = null,
        @Field("note") note: String? = null,

        @HeaderMap header: HashMap<String, String>
    ): Call<TicketState>

    @DELETE("/api/v1/ticket_states/{id}")
    fun deleteTicketState(
        @Path("id") id: Int,
        @HeaderMap header: HashMap<String, String>
    ): Call<ResponseBody>

    @GET("/api/v1/ticket_priorities")
    fun ticketPriorities(
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<TicketPriority>>

    @GET("/api/v1/ticket_priorities/{id}")
    fun ticketPriority(
        @Path("id") id: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<TicketPriority>

    @FormUrlEncoded
    @PUT("/api/v1/ticket_priorities/{id}")
    fun updateTicketPriority(
        @Path("id") id: Int,
        @Body body: TicketPriority,
        @HeaderMap header: HashMap<String, String>
    ): Call<TicketPriority>

    @FormUrlEncoded
    @PUT("/api/v1/ticket_priorities/{id}")
    fun updateTicketPriority(
        @Path("id") id: Int,

        @Field("name") name: String? = null,
        @Field("active") active: Boolean? = null,
        @Field("note") note: String? = null,

        @HeaderMap header: HashMap<String, String>
    ): Call<TicketPriority>

    @DELETE("/api/v1/ticket_priorities/{id}")
    fun deleteTicketPriority(
        @Path("id") id: Int,
        @HeaderMap header: HashMap<String, String>
    ): Call<ResponseBody>

    @GET("/api/v1/tickets")
    fun tickets(
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<Ticket>>

    @GET("/api/v1/tickets/{id}")
    fun ticket(
        @Path("id") id: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<Ticket>

    @POST("/api/v1/tickets")
    fun createTicket(
        @Body body: TicketCompat,
        @HeaderMap header: HashMap<String, String>
    ): Call<Ticket>

    @FormUrlEncoded
    @PUT("/api/v1/tickets/{id}")
    fun updateTicket(
        @Path("id") id: Int,
        @Body body: Ticket,
        @HeaderMap header: HashMap<String, String>
    ): Call<Ticket>

    @FormUrlEncoded
    @PUT("/api/v1/tickets/{id}")
    fun updateTicket(
        @Path("id") id: Int,

        @Field("title") title: String? = null,
        @Field("group_id") groupId: Int? = null,
        @Field("group") group: String? = null,
        @Field("state_id") stateId: Int? = null,
        @Field("state") state: String? = null,
        @Field("priority_id") priorityId: Int? = null,
        @Field("priority") priority: String? = null,
        @Field("owner_id") ownerId: Int? = null,
        @Field("owner") owner: String? = null,
        @Field("customer_id") customerId: Int? = null,
        @Field("customer") customer: String? = null,
        @Field("note") note: String? = null,

        @HeaderMap header: HashMap<String, String>
    ): Call<Ticket>

    @DELETE("/api/v1/tickets/{id}")
    fun deleteTicket(
        @Path("id") id: Int,
        @HeaderMap header: HashMap<String, String>
    ): Call<ResponseBody>

    @GET("/api/v1/tickets/search")
    fun searchTickets(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<SearchResult>

    @GET("/api/v1/ticket_articles/by_ticket/{ticketId}")
    fun ticketArticles(
        @Path("ticketId") ticketId: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<List<TicketArticle>>

    @GET("/api/v1/ticket_articles/{id}")
    fun ticketArticle(
        @Path("id") id: Int,
        @Query("expand") expanded: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<TicketArticle>

    @FormUrlEncoded
    @POST("/api/v1/ticket_articles")
    fun createTicketArticle(
        @Field("ticket_id") ticketId: Int,
        @Field("to") to: String?,
        @Field("cc") cc: String?,
        @Field("subject") subject: String,
        @Field("body") body: String,
        @Field("content_type") contentType: String,
        @Field("type") type: String,
        @Field("internal") internal: Boolean,
        @Field("time_unit") timeUnit: String,
        @HeaderMap header: HashMap<String, String>
    ): Call<TicketArticle>

    @Streaming
    @GET("/api/v1/ticket_attachment/{ticket_id}/{article_id}/{id}")
    fun ticketArticleAttachment(
        @Path("ticket_id") ticketId: Int,
        @Path("article_id") articleId: Int,
        @Path("id") attachmentId: Int,
        @HeaderMap header: HashMap<String, String>
    ): Call<ResponseBody>

    @GET("/api/v1/object_manager_attributes")
    fun objects(
        @HeaderMap header: HashMap<String, String>
    ): Call<List<Object>>

    @GET("/api/v1/object_manager_attributes/{id}")
    fun `object`(
        @Path("id") id: Int,
        @HeaderMap header: HashMap<String, String>
    ): Call<Object>

    @GET("/api/v1/online_notifications")
    fun onlineNotifications(
        @HeaderMap header: HashMap<String, String>
    ): Call<List<OnlineNotification>>

    @GET("/api/v1/online_notifications/{id}")
    fun onlineNotification(
        @Path("id") id: Int,
        @HeaderMap header: HashMap<String, String>
    ): Call<OnlineNotification>

    @FormUrlEncoded
    @PUT("/api/v1/online_notifications/{id}")
    fun updateOnlineNotification(
        @Path("id") id: Int,
        @Field("seen") seen: Boolean,
        @HeaderMap header: HashMap<String, String>
    ): Call<OnlineNotification>

    @DELETE("/api/v1/online_notifications/{id}")
    fun deleteOnlineNotification(
        @Path("id") id: Int,
        @HeaderMap header: HashMap<String, String>
    ): Call<ResponseBody>

    @POST("/api/v1/online_notifications/mark_all_as_read")
    fun markAllOnlineNotificationsAsRead(
        @HeaderMap header: HashMap<String, String>
    ): Call<Any>
}
