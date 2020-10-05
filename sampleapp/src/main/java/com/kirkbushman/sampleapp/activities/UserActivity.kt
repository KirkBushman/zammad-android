package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.User
import kotlinx.android.synthetic.main.activity_detail.*

class UserActivity : AppCompatActivity(R.layout.activity_detail) {

    companion object {

        private const val PARAM_USER = "intent_param_user"

        fun start(context: Context, user: User) {

            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra(PARAM_USER, user)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val user by lazy { intent.getParcelableExtra<User>(PARAM_USER)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var newUser: User? = null
        doAsync(
            doWork = {
                newUser = client?.user(user.id, true)
            },
            onPost = {
                model_text.text = newUser.toString()
            }
        )
    }
}
