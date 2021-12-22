package com.kirkbushman.zammad.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@JsonClass(generateAdapter = true)
@Parcelize
data class TicketTags(

    @Json(name = "tags")
    val tags: @RawValue List<String?>

) : Parcelable