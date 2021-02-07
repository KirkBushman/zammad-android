package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.controllers.ObjectsController
import com.kirkbushman.sampleapp.callbacks.OnClickCallback
import com.kirkbushman.sampleapp.databinding.ActivityObjectsBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Object
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ObjectsActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private val objects = ArrayList<Object>()
    private val controller by lazy {
        ObjectsController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val `object` = objects[position]
                ObjectActivity.start(this@ObjectsActivity, `object`)
            }
        })
    }

    private lateinit var binding: ActivityObjectsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityObjectsBinding.inflate(layoutInflater)
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
                objects.addAll(client.objects() ?: emptyList())
            },
            onPost = {
                controller.setItems(objects)
            }
        )
    }
}
