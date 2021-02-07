package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.callbacks.OnClickCallback
import com.kirkbushman.sampleapp.controllers.TagsController
import com.kirkbushman.sampleapp.databinding.ActivityTagsBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Tag
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TagsActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private val tags = ArrayList<Tag>()
    private val controller by lazy {
        TagsController(object : OnClickCallback {

            override fun onClick(position: Int) {}
        })
    }

    private lateinit var binding: ActivityTagsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTagsBinding.inflate(layoutInflater)
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
                tags.addAll(client.tags() ?: listOf())
            },
            onPost = {
                controller.setItems(tags)
            }
        )
    }
}
