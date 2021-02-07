package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.controllers.NotificationsController
import com.kirkbushman.sampleapp.callbacks.OnClickCallback
import com.kirkbushman.sampleapp.databinding.ActivityNotificationsBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.OnlineNotification
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationsActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private val notifications = ArrayList<OnlineNotification>()
    private val controller by lazy {
        NotificationsController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val notification = notifications[position]
                NotificationActivity.start(this@NotificationsActivity, notification)
            }
        })
    }

    private lateinit var binding: ActivityNotificationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.list.setHasFixedSize(true)
        binding.list.setController(controller)

        DoAsync(
            doWork = {
                notifications.addAll(client.onlineNotifications(true) ?: listOf())
            },
            onPost = {
                controller.setItems(notifications)
            }
        )
    }
}
