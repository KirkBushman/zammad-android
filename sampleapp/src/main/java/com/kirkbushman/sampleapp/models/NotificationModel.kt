package com.kirkbushman.sampleapp.models

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.zammad.models.OnlineNotification

@EpoxyModelClass(layout = R.layout.item_notification)
abstract class NotificationModel : EpoxyModelWithHolder<NotificationHolder>() {

    @EpoxyAttribute
    lateinit var notification: OnlineNotification

    override fun bind(holder: NotificationHolder) {

        holder.notificationText.text = notification.id.toString()
    }
}

class NotificationHolder : KotlinHolder() {

    val notificationText by bind<TextView>(R.id.notification_text)
}
