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
data class Object(

    @Json(name = "id")
    override val id: Int,

    @Json(name = "object_lookup_id")
    val objectLookupId: Int?,

    @Json(name = "name")
    val name: String,

    @Json(name = "display")
    val display: String,

    @Json(name = "data_type")
    val dataType: String,

    @Json(name = "active")
    val active: Boolean,

    @Json(name = "editable")
    val editable: Boolean,

    @Json(name = "deletable")
    val deletable: Boolean?,

    @Json(name = "position")
    val position: Int,

    @Json(name = "to_create")
    val toCreate: Boolean,

    @Json(name = "to_migrate")
    val toMigrate: Boolean,

    @Json(name = "to_delete")
    val toDelete: Boolean,

    @Json(name = "to_config")
    val toConfig: Boolean,

    @Json(name = "object")
    val `object`: String?,

    @Json(name = "created_at")
    override val createdAt: String,

    @Json(name = "updated_at")
    override val updatedAt: String,

    @Json(name = "created_by_id")
    override val createdById: Int,

    @Json(name = "updated_by_id")
    override val updatedById: Int,

    @Json(name = "created_by")
    override val createdBy: String?,

    @Json(name = "updated_by")
    override val updatedBy: String?

) : Parcelable, Identifiable, Creatable, Updatable
