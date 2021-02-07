package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.callbacks.OnUpDelCallback
import com.kirkbushman.sampleapp.controllers.UsersController
import com.kirkbushman.sampleapp.databinding.ActivityUsersBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UsersActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private val users = ArrayList<User>()
    private val controller by lazy {
        UsersController(object : OnUpDelCallback {

            override fun onClick(position: Int) {

                val user = users[position]
                UserActivity.start(this@UsersActivity, user)
            }

            override fun onUpdateClick(position: Int) {

                val user = users[position]
                UserUpdateActivity.start(this@UsersActivity, user)
            }

            override fun onDeleteClick(position: Int) {

                DoAsync(
                    doWork = {

                        val user = users[position]
                        client.deleteUser(user)
                    },
                    onPost = {
                        showToast("User deleted!")
                    }
                )
            }
        })
    }

    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUsersBinding.inflate(layoutInflater)
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
                users.addAll(client.users() ?: listOf())
            },
            onPost = {
                controller.setItems(users)
            }
        )
    }
}
