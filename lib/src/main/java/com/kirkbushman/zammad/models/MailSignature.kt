package com.kirkbushman.zammad.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class MailSignature(

    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "body")
    val body: String,

    @Json(name = "active")
    val active: Boolean,

    @Json(name = "note")
    val note: String?,

    @Json(name = "created_by_id")
    val createdById: Int,

    @Json(name = "updated_by_id")
    val updatedById: Int,

    @Json(name = "created_at")
    val createdAt: String,

    @Json(name = "updated_at")
    val updatedAt: String,

) : Parcelable

