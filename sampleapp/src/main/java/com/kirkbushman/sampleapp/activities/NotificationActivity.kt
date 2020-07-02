package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.OnlineNotification
import kotlinx.android.synthetic.main.activity_detail.*

class NotificationActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_NOTIFICATION = "intent_param_notification"

        fun start(context: Context, notification: OnlineNotification) {

            val intent = Intent(context, NotificationActivity::class.java)
            intent.putExtra(PARAM_NOTIFICATION, notification)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val notification by lazy { intent.getParcelableExtra(PARAM_NOTIFICATION) as OnlineNotification }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var newNotification: OnlineNotification? = null
        doAsync(
            doWork = {
                newNotification = client?.onlineNotification(notification.id, true)
            },
            onPost = {
                model_text.text = newNotification.toString()
            }
        )
    }
}
