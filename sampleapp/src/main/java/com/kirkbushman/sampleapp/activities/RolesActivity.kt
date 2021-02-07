package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.callbacks.OnClickCallback
import com.kirkbushman.sampleapp.controllers.RolesController
import com.kirkbushman.sampleapp.databinding.ActivityRolesBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Role
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RolesActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private val roles = ArrayList<Role>()
    private val controller by lazy {
        RolesController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val role = roles[position]
                RoleActivity.start(this@RolesActivity, role)
            }
        })
    }

    private lateinit var binding: ActivityRolesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRolesBinding.inflate(layoutInflater)
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
                roles.addAll(client.roles() ?: listOf())
            },
            onPost = {
                controller.setItems(roles)
            }
        )
    }
}
