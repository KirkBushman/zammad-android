package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.GroupsController
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Group
import kotlinx.android.synthetic.main.activity_groups.*

class GroupsActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    private val groups = ArrayList<Group>()
    private val controller by lazy {
        GroupsController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val group = groups[position]
                GroupActivity.start(this@GroupsActivity, group)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(doWork = {

            groups.addAll(client?.groups() ?: listOf())
        }, onPost = {

            controller.setGroups(groups)
        })
    }
}
