package com.kirkbushman.zammad.models

import android.os.Parcelable
import com.kirkbushman.zammad.models.base.Creatable
import com.kirkbushman.zammad.models.base.Identifiable
import com.kirkbushman.zammad.models.base.Updatable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@JsonClass(generateAdapter = true)
@Parcelize
data class Ticket(

    @Json(name = "id")
    override val id: Int,

    @Json(name = "title")
    val title: String,

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
    override val updatedBy: String?

) : Parcelable, Identifiable, Creatable, Updatable {

    override fun hashCode() = id.hashCode()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ticket

        if (id != other.id) return false

        return true
    }

    fun getDateString(): Date? {
        return dateFromString(createdAt)
    }

    private fun dateFromString(dateStr: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return format.parse(dateStr.replace('T', ' ').replace('Z', ' '))
    }
}
