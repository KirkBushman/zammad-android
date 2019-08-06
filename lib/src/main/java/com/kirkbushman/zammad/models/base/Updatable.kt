package com.kirkbushman.zammad.models.base

interface Updatable {

    val updatedAt: String

    val updatedById: Int

    val updatedBy: String?
}
