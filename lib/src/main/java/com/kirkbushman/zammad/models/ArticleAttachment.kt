package com.kirkbushman.zammad.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@JsonClass(generateAdapter = true)
@Parcelize
data class ArticleAttachment(

    @Json(name = "id")
    val id: Int,

    @Json(name = "filename")
    val filename: String,

    @Json(name = "size")
    val size: Int,

    @Json(name = "preferences")
    val preferences: @RawValue Map<String, Any>

) : Parcelable {

    fun hasContentType(): Boolean {
        return preferences.containsKey("Content-Type")
    }

    fun getContentType(): String? {
        return preferences["Content-Type"] as String?
    }

    fun hasMimeType(): Boolean {
        return preferences.containsKey("Mime-Type")
    }

    fun getMimeType(): String? {
        return preferences["Mime-Type"] as String?
    }

    fun hasCharset(): Boolean {
        return preferences.containsKey("Charset")
    }

    fun getCharset(): String? {
        return preferences["Charset"] as String?
    }
}
