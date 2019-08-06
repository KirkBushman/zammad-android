package com.kirkbushman.zammad.models

import android.os.Parcelable
import com.kirkbushman.zammad.models.base.Creatable
import com.kirkbushman.zammad.models.base.Identifiable
import com.kirkbushman.zammad.models.base.Updatable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Group(

    @Json(name = "id")
    override val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "signature_id")
    val signatureId: Int?,

    @Json(name = "email_address_id")
    val emailAddressId: Int?,

    @Json(name = "active")
    val active: Boolean,

    @Json(name = "follow_up_possible")
    val follow_up_possible: String,

    @Json(name = "follow_up_assignment")
    val followUpAssignment: Boolean,

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

    @Json(name = "user_ids")
    val userIds: List<Int>,

    @Json(name = "users")
    val users: List<String>?

) : Parcelable, Identifiable, Creatable, Updatable {

    override fun hashCode() = id
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Group

        if (id != other.id) return false

        return true
    }
}
