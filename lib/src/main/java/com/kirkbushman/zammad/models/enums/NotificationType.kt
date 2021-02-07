package com.kirkbushman.zammad.models.enums

import com.squareup.moshi.Json

enum class NotificationType(val typeStr: String) {

    @Json(name = "create") CREATE("create"),
    @Json(name = "update") UPDATE("update"),
    @Json(name = "escalation") ESCALATION("escalation")
}
