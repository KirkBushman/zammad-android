package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.callbacks.OnClickCallback
import com.kirkbushman.sampleapp.controllers.OverviewsController
import com.kirkbushman.sampleapp.databinding.ActivityOverviewsBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Overview
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OverviewsActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private val overviews = ArrayList<Overview>()
    private val controller by lazy {
        OverviewsController(object : OnClickCallback {

            override fun onClick(position: Int) {

                val overview = overviews[position]
                OverviewActivity.start(this@OverviewsActivity, overview)
            }
        })
    }

    private lateinit var binding: ActivityOverviewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOverviewsBinding.inflate(layoutInflater)
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
                overviews.addAll(client.overviews() ?: listOf())
            },
            onPost = {
                controller.setItems(overviews)
            }
        )
    }
}
