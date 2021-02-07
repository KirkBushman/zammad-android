package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.OnlineNotification
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_NOTIFICATION = "intent_param_notification"

        fun start(context: Context, notification: OnlineNotification) {

            val intent = Intent(context, NotificationActivity::class.java)
            intent.putExtra(PARAM_NOTIFICATION, notification)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val notification by lazy { intent.getParcelableExtra<OnlineNotification>(PARAM_NOTIFICATION)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newNotification: OnlineNotification? = null
        DoAsync(
            doWork = {
                newNotification = client.onlineNotification(notification.id, true)
            },
            onPost = {
                binding.modelText.text = newNotification.toString()
            }
        )
    }
}
