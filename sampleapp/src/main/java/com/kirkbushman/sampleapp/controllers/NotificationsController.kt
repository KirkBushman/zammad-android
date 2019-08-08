package com.kirkbushman.sampleapp.controllers

import com.airbnb.epoxy.EpoxyController
import com.kirkbushman.sampleapp.models.empty
import com.kirkbushman.sampleapp.models.notification
import com.kirkbushman.zammad.models.OnlineNotification

class NotificationsController : EpoxyController() {

    private val notifications = ArrayList<OnlineNotification>()

    fun setNotifications(notifications: Collection<OnlineNotification>) {
        this.notifications.clear()
        this.notifications.addAll(notifications)
        requestModelBuild()
    }

    override fun buildModels() {

        if (notifications.isEmpty()) {
            empty {
                id("empty_items")
            }
        }

        notifications.forEach {

            notification {
                id(it.id)
                notification(it)
            }
        }
    }
}
