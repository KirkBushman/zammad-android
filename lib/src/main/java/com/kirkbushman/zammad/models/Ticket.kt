package com.kirkbushman.zammad.models

import android.os.Parcelable
import com.kirkbushman.zammad.models.base.Creatable
import com.kirkbushman.zammad.models.base.Identifiable
import com.kirkbushman.zammad.models.base.Updatable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Ticket(

    @Json(name = "id")
    override val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "number")
    val number: String,

    @Json(name = "group_id")
    val groupId: Int,

    @Json(name = "group")
    val group: String?,

    @Json(name = "state_id")
    val stateId: Int,

    @Json(name = "state")
    val state: String?,

    @Json(name = "priority_id")
    val priorityId: Int,

    @Json(name = "priority")
    val priority: String?,

    @Json(name = "article_ids")
    val articleIds: List<Int>?,

    @Json(name = "article_count")
    val articleCount: Int,

    @Json(name = "owner_id")
    val ownerId: Int,

    @Json(name = "owner")
    val owner: String?,

    @Json(name = "customer_id")
    val customerId: Int,

    @Json(name = "customer")
    val customer: String?,

    @Json(name = "organization_id")
    val organizationId: Int?,

    @Json(name = "organization")
    val organization: String?,

    @Json(name = "note")
    val note: String?,

    @Json(name = "created_at")
    override val createdAt: String,

    @Json(name = "updated_at")
    override val updatedAt: String,

    @Json(name = "created_by_id")
    override val createdById: Int,

    @Json(name = "updated_by_id")
    override val updatedById: Int,

    @Json(name = "created_by")
    override val createdBy: String?,

    @Json(name = "updated_by")
    override val updatedBy: String?,

    @Json(name = "pending_time")
    val pendingTime: String?

) : Parcelable, Identifiable, Creatable, Updatable
