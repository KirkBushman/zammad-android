package com.kirkbushman.zammad.models.compat

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class TicketArticleCompat(

    @Json(name = "subject")
    val subject: String,

    @Json(name = "body")
    val body: String,

    @Json(name = "type")
    val type: String,

    @Json(name = "internal")
    val internal: Boolean

) : Parcelable
