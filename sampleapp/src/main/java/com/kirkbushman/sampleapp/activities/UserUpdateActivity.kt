package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityUserUpdateBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_USER = "intent_param_user"

        fun start(context: Context, user: User) {

            val intent = Intent(context, UserUpdateActivity::class.java)
            intent.putExtra(PARAM_USER, user)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val user by lazy { intent.getParcelableExtra<User>(PARAM_USER)!! }

    private lateinit var binding: ActivityUserUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userFirstname.setText(user.firstname)
        binding.userLastname.setText(user.lastname)
        binding.userActive.isChecked = user.active
        binding.userEmail.setText(user.email)
        binding.userPhone.setText(user.phone)
        binding.userMobile.setText(user.mobile)
        binding.userNote.setText(user.note)

        binding.bttnSubmit.setOnClickListener {

            val firstname = binding.userFirstname.text.toString()
            val lastname = binding.userLastname.text.toString()
            val active = binding.userActive.isChecked
            val email = binding.userEmail.text.toString()
            val phone = binding.userPhone.text.toString()
            val mobile = binding.userMobile.text.toString()
            val note = binding.userNote.text.toString()

            DoAsync(
                doWork = {

                    client.updateUser(
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
