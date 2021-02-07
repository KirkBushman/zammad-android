package com.kirkbushman.sampleapp.models

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.models.base.KotlinHolder
import com.kirkbushman.zammad.models.OnlineNotification

@EpoxyModelClass(layout = R.layout.item_notification)
abstract class NotificationModel : EpoxyModelWithHolder<NotificationHolder>() {

    @EpoxyAttribute
    lateinit var notification: OnlineNotification

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: NotificationHolder) {

        holder.notificationObj.text = notification.obj
        holder.notificationType.text = notification.type?.typeStr ?: ""
        holder.notificationUser.text = notification.user ?: ""
        holder.notificationCreated.text = notification.createdAt

        holder.container.setOnClickListener(clickListener)
    }

    override fun unbind(holder: NotificationHolder) {
        holder.container.setOnClickListener(null)
    }
}

class NotificationHolder : KotlinHolder() {

    val container by bind<LinearLayout>(R.id.container)
    val notificationObj by bind<TextView>(R.id.notification_obj)
    val notificationType by bind<TextView>(R.id.notification_type)
    val notificationUser by bind<TextView>(R.id.notification_user)
    val notificationCreated by bind<TextView>(R.id.notification_created)
}
