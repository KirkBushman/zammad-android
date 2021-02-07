package com.kirkbushman.sampleapp.controllers

import com.kirkbushman.sampleapp.callbacks.OnClickCallback
import com.kirkbushman.sampleapp.models.notification
import com.kirkbushman.zammad.models.OnlineNotification

class NotificationsController(callback: OnClickCallback) : BaseController<OnlineNotification>(callback) {

    override fun onItem(item: OnlineNotification) {

        notification {
            id(item.id)
            notification(item)
            clickListener { _, _, _, position -> callback.onClick(position) }
        }
    }
}
