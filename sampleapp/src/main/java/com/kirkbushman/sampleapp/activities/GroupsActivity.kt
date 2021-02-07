package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.controllers.GroupsController
import com.kirkbushman.sampleapp.callbacks.OnUpDelCallback
import com.kirkbushman.sampleapp.databinding.ActivityGroupsBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Group
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GroupsActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private val groups = ArrayList<Group>()
    private val controller by lazy {
        GroupsController(object : OnUpDelCallback {

            override fun onClick(position: Int) {

                val group = groups[position]
                GroupActivity.start(this@GroupsActivity, group)
            }

            override fun onUpdateClick(position: Int) {

                val group = groups[position]
                GroupUpdateActivity.start(this@GroupsActivity, group)
            }

            override fun onDeleteClick(position: Int) {

                DoAsync(
                    doWork = {

                        val group = groups[position]
                        client.deleteGroup(group)
                    },
                    onPost = {
                        showToast("Group deleted!")
                    }
                )
            }
        })
    }

    private lateinit var binding: ActivityGroupsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGroupsBinding.inflate(layoutInflater)
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
                groups.addAll(client.groups() ?: listOf())
            },
            onPost = {
                controller.setItems(groups)
            }
        )
    }
}
