package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.showToast
import com.kirkbushman.zammad.models.User
import kotlinx.android.synthetic.main.activity_user_update.*

class UserUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_USER = "intent_param_user"

        fun start(context: Context, user: User) {

            val intent = Intent(context, UserUpdateActivity::class.java)
            intent.putExtra(PARAM_USER, user)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val user by lazy { intent.getParcelableExtra<User>(PARAM_USER)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_update)

        user_firstname.setText(user.firstname)
        user_lastname.setText(user.lastname)
        user_active.isChecked = user.active
        user_email.setText(user.email)
        user_phone.setText(user.phone)
        user_mobile.setText(user.mobile)
        user_note.setText(user.note)

        bttn_submit.setOnClickListener {

            val firstname = user_firstname.text.toString()
            val lastname = user_lastname.text.toString()
            val active = user_active.isChecked
            val email = user_email.text.toString()
            val phone = user_phone.text.toString()
            val mobile = user_mobile.text.toString()
            val note = user_note.text.toString()

            doAsync(
                doWork = {

                    client?.updateUser(
                        id = user.id,
                        firstname = firstname,
                        lastname = lastname,
                        active = active,
                        email = email,
                        phone = phone,
                        mobile = mobile,
                        note = note
                    )
                },
                onPost = {

                    showToast("User Updated!")
                }
            )
        }
    }
}
