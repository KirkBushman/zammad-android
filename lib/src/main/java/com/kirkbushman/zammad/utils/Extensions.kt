package com.kirkbushman.zammad.utils

import com.kirkbushman.zammad.models.base.Creatable
import com.kirkbushman.zammad.models.base.Updatable
import com.kirkbushman.zammad.utils.Utils.convertStringToDate
import java.util.*

fun Creatable.createdAtDate(): Date? {
    return convertStringToDate(createdAt)
}

fun Updatable.updatedAtDate(): Date? {
    return convertStringToDate(updatedAt)
}
