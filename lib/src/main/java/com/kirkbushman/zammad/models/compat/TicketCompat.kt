package com.kirkbushman.zammad.models.compat

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class TicketCompat(

    @Json(name = "title")
    val title: String,

    @Json(name = "group_id")
    val groupId: Int? = null,

    @Json(name = "group")
    val group: String? = null,

    @Json(name = "state_id")
    val stateId: Int? = null,

    @Json(name = "state")
    val state: String? = null,

    @Json(name = "priority_id")
    val priorityId: Int? = null,

    @Json(name = "priority")
    val priority: String? = null,

    @Json(name = "customer_id")
    val customerId: Int? = null,

    @Json(name = "customer")
    val customer: String? = null,

    @Json(name = "owner_id")
    val ownerId: Int? = null,

    @Json(name = "owner")
    val owner: String? = null,

    @Json(name = "article")
    val article: TicketArticleCompat,

    @Json(name = "note")
    val note: String? = null

) : Parcelable
