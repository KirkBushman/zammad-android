package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_USER = "intent_param_user"

        fun start(context: Context, user: User) {

            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra(PARAM_USER, user)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val user by lazy { intent.getParcelableExtra<User>(PARAM_USER)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newUser: User? = null
        DoAsync(
            doWork = {
                newUser = client.user(user.id, true)
            },
            onPost = {
                binding.modelText.text = newUser.toString()
            }
        )
    }
}
