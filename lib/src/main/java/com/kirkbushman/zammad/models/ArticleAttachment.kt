package com.kirkbushman.zammad.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ArticleAttachment(

    @Json(name = "id")
    val id: Int,

    @Json(name = "filename")
    val filename: String,

    @Json(name = "size")
    val size: Int

) : Parcelable {

    override fun hashCode(): Int = id
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticleAttachment

        if (id != other.id) return false

        return true
    }
}
