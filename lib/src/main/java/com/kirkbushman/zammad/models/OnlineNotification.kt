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
data class OnlineNotification(

    @Json(name = "id")
    override val id: Int,

    @Json(name = "o_id")
    val objId: Int,

    @Json(name = "object")
    val objectType: String?,

    @Json(name = "type")
    val type: NotificationType?,

    @Json(name = "seen")
    val seen: Boolean,

    @Json(name = "updated_at")
    override val updatedAt: String,

    @Json(name = "created_at")
    override val createdAt: String,

    @Json(name = "created_by")
    override val createdBy: String?,

    @Json(name = "updated_by")
    override val updatedBy: String?,

    @Json(name = "updated_by_id")
    override val updatedById: Int,

    @Json(name = "created_by_id")
    override val createdById: Int

) : Parcelable, Identifiable, Creatable, Updatable

enum class NotificationType(val typeStr: String) {

    CREATE("create"),
    UPDATE("update"),
    ESCALATION("escalation")
}
