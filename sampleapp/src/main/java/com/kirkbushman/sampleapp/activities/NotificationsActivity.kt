package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.NotificationsController
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.OnlineNotification
import kotlinx.android.synthetic.main.activity_users.*

class NotificationsActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    private val notifications = ArrayList<OnlineNotification>()
    private val controller by lazy {
        NotificationsController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val notification = notifications[position]
                NotificationActivity.start(this@NotificationsActivity, notification)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(doWork = {

            notifications.addAll(client?.onlineNotifications(true) ?: listOf())
        }, onPost = {

            controller.setItems(notifications)
        })
    }
}
