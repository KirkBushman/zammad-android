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
data class Organization(

    @Json(name = "id")
    override val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "shared")
    val shared: Boolean,

    @Json(name = "domain")
    val domain: String,

    @Json(name = "domain_assignment")
    val domainAssignment: Boolean,

    @Json(name = "active")
    val active: Boolean,

    @Json(name = "note")
    val note: String,

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

    @Json(name = "member_ids")
    val memberIds: List<Int>,

    @Json(name = "members")
    val members: List<String>?

) : Parcelable, Identifiable, Creatable, Updatable
