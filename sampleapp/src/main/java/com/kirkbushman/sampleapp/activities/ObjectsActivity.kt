package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.ObjectsController
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Object
import kotlinx.android.synthetic.main.activity_objects.*

class ObjectsActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    private val objects = ArrayList<Object>()
    private val controller by lazy {
        ObjectsController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val `object` = objects[position]
                ObjectActivity.start(this@ObjectsActivity, `object`)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objects)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(doWork = {

            objects.addAll(client?.objects() ?: emptyList())
        }, onPost = {

            controller.setItems(objects)
        })
    }
}
