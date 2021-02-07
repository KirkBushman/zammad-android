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
data class Overview(

    @Json(name = "id")
    override val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "link")
    val link: String,

    @Json(name = "active")
    val active: Boolean,

    @Json(name = "prio")
    val priority: Int,

    @Json(name = "order")
    val order: OverviewOrder,

    @Json(name = "view")
    val view: OverviewView,

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

    @Json(name = "role_ids")
    val roleIds: List<Int>,

    @Json(name = "roles")
    val roles: List<String>?,

    @Json(name = "user_ids")
    val userIds: List<Int>,

    @Json(name = "users")
    val users: List<String>?

) : Parcelable, Identifiable, Creatable, Updatable

@JsonClass(generateAdapter = true)
@Parcelize
data class OverviewOrder(

    @Json(name = "by")
    val by: String,

    @Json(name = "direction")
    val direction: String

) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class OverviewView(

    @Json(name = "d")
    val d: List<String>?,

    @Json(name = "s")
    val s: List<String>?,

    @Json(name = "m")
    val m: List<String>?,

    @Json(name = "view_mode_default")
    val viewModeDefault: String?

) : Parcelable
