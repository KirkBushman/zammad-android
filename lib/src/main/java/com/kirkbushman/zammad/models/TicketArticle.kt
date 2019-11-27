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
data class TicketArticle(

    @Json(name = "id")
    override val id: Int,

    @Json(name = "ticket_id")
    val ticketId: String,

    @Json(name = "sender_id")
    val senderId: Int?,

    @Json(name = "from")
    val from: String,

    @Json(name = "to")
    val to: String? = "",

    @Json(name = "cc")
    val cc: String? = "",

    @Json(name = "subject")
    val subject: String?,

    @Json(name = "reply_to")
    val replyTo: String?,

    @Json(name = "in_reply_to")
    val inReplyTo: String?,

    @Json(name = "message_id")
    val messageId: String?,

    @Json(name = "message_id_md5")
    val messageIdMd5: String?,

    @Json(name = "references")
    val references: String?,

    @Json(name = "body")
    val body: String,

    @Json(name = "content_type")
    val contentType: String,

    @Json(name = "type")
    val type: String,

    @Json(name = "sender")
    val sender: String,

    @Json(name = "internal")
    val internal: Boolean,

    @Json(name = "attachments")
    val attachments: List<ArticleAttachment>,

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
    override val updatedBy: String?

) : Parcelable, Identifiable, Creatable, Updatable {

    fun hasAttachments(): Boolean {
        return attachments.isNotEmpty()
    }
}
