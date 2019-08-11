package com.kirkbushman.zammad.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchResult(

    @Json(name = "tickets")
    val tickets: List<Int>,

    @Json(name = "tickets_count")
    val ticketsCount: Int,

    @Json(name = "assets")
    val assets: SearchAssets

) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchAssets(

    @Json(name = "Ticket")
    val tickets: Map<String, Ticket>,

    @Json(name = "User")
    val users: Map<String, User>,

    @Json(name = "Role")
    val roles: Map<String, Role>,

    @Json(name = "Group")
    val groups: Map<String, Group>,

    @Json(name = "Organization")
    val organizations: Map<String, Organization>

) : Parcelable
