package com.kirkbushman.zammad.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class TicketArticle(

    @Json(name = "id")
    val id: Int,

    @Json(name = "ticket_id")
    val ticketId: String,

    @Json(name = "from")
    val from: String,

    @Json(name = "to")
    val to: String? = "",

    @Json(name = "cc")
    val cc: String? = "",

    @Json(name = "subject")
    val subject: String?,

    @Json(name = "body")
    val body: String,

    @Json(name = "content_type")
    val contentType: String,

    @Json(name = "type")
    val type: String,

    @Json(name = "internal")
    val internal: Boolean,

    @Json(name = "attachments")
    val attachment: List<ArticleAttachment>

) : Parcelable {

    override fun hashCode(): Int = id
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TicketArticle

        if (id != other.id) return false

        return true
    }

    fun hasAttachments(): Boolean {
        return attachment.isNotEmpty()
    }
}
