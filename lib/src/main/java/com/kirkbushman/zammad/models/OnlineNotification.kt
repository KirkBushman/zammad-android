package com.kirkbushman.zammad.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class OnlineNotification(

    @Json(name = "id")
    val id: Int,

    @Json(name = "o_id")
    val objId: Int,

    @Json(name = "object")
    val objectType: String,

    @Json(name = "type")
    val type: String,

    @Json(name = "seen")
    val seen: Boolean,

    @Json(name = "updated_by_id")
    val updatedById: Int,

    @Json(name = "created_by_id")
    val createdById: Int,

    @Json(name = "updated_at")
    val updatedAt: String,

    @Json(name = "created_at")
    val createdAt: String

) : Parcelable {

    override fun hashCode(): Int = id
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OnlineNotification

        if (id != other.id) return false

        return true
    }
}
