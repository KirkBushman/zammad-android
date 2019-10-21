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
data class User(

    @Json(name = "id")
    override val id: Int,

    @Json(name = "organization_id")
    val organizationId: Int?,

    @Json(name = "organization")
    val organization: String?,

    @Json(name = "active")
    val active: Boolean,

    @Json(name = "login")
    val login: String,

    @Json(name = "firstname")
    val firstname: String,

    @Json(name = "lastname")
    val lastname: String,

    @Json(name = "email")
    val email: String,

    @Json(name = "image")
    val image: String?,

    @Json(name = "image_source")
    val imageSource: String?,

    @Json(name = "web")
    val web: String,

    @Json(name = "phone")
    val phone: String,

    @Json(name = "fax")
    val fax: String,

    @Json(name = "mobile")
    val mobile: String,

    @Json(name = "department")
    val department: String,

    @Json(name = "street")
    val street: String,

    @Json(name = "zip")
    val zip: String,

    @Json(name = "city")
    val city: String,

    @Json(name = "country")
    val country: String,

    @Json(name = "address")
    val address: String,

    @Json(name = "vip")
    val isVip: Boolean,

    @Json(name = "verified")
    val isVerified: Boolean,

    @Json(name = "note")
    val note: String,

    @Json(name = "source")
    val source: String?,

    @Json(name = "last_login")
    val lastLogin: String?,

    @Json(name = "login_failed")
    val loginFailed: Int,

    @Json(name = "out_of_office")
    val outOfOffice: Boolean,

    @Json(name = "out_of_office_start_at")
    val outOfOfficeStartAt: String?,

    @Json(name = "out_of_office_end_at")
    val outOfOfficeEndAt: String?,

    @Json(name = "out_of_office_replacement_id")
    val outOfOfficeReplacementId: Int?,

    @Json(name = "created_by_id")
    override val createdById: Int,

    @Json(name = "updated_by_id")
    override val updatedById: Int,

    @Json(name = "created_at")
    override val createdAt: String,

    @Json(name = "updated_at")
    override val updatedAt: String,

    @Json(name = "created_by")
    override val createdBy: String?,

    @Json(name = "updated_by")
    override val updatedBy: String?,

    @Json(name = "role_ids")
    val roleIds: List<Int>,

    @Json(name = "roles")
    val roles: List<String>?,

    @Json(name = "organization_ids")
    val organizationIds: List<Int>,

    @Json(name = "organizations")
    val organizations: List<String>?,

    @Json(name = "authorization_ids")
    val authorizationIds: List<Int>,

    @Json(name = "authorizations")
    val authorizations: List<String>?

) : Parcelable, Identifiable, Creatable, Updatable {

    override fun hashCode() = id.hashCode()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }
}
