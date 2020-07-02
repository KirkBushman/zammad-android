package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.controllers.RolesController
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Role
import kotlinx.android.synthetic.main.activity_groups.*

class RolesActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    private val roles = ArrayList<Role>()
    private val controller by lazy {
        RolesController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val role = roles[position]
                RoleActivity.start(this@RolesActivity, role)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roles)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(
            doWork = {
                roles.addAll(client?.roles() ?: listOf())
            },
            onPost = {
                controller.setItems(roles)
            }
        )
    }
}
