package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.OnClickCallback
import com.kirkbushman.sampleapp.controllers.OverviewsController
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Overview
import kotlinx.android.synthetic.main.activity_tickets.*

class OverviewsActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    private val overviews = ArrayList<Overview>()
    private val controller by lazy {
        OverviewsController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val overview = overviews[position]
                OverviewActivity.start(this@OverviewsActivity, overview)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overviews)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(
            doWork = {
                overviews.addAll(client?.overviews() ?: listOf())
            },
            onPost = {
                controller.setItems(overviews)
            }
        )
    }
}
