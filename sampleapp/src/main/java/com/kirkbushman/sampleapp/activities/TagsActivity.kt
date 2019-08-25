package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.controllers.TagsController
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Tag
import kotlinx.android.synthetic.main.activity_tags.*

class TagsActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    private val tags = ArrayList<Tag>()
    private val controller by lazy {
        TagsController(object : OnClickCallback {

            override fun onClick(position: Int) {}
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tags)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(doWork = {

            tags.addAll(client?.tags() ?: listOf())
        }, onPost = {

            controller.setTags(tags)
        })
    }
}
