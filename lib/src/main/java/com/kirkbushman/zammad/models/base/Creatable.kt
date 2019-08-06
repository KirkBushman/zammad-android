package com.kirkbushman.zammad.models.base

interface Creatable {

    val createdAt: String

    val createdById: Int

    val createdBy: String?
}
