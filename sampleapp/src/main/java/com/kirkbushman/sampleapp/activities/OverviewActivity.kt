package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Overview
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OverviewActivity : AppCompatActivity(R.layout.activity_detail) {

    companion object {

        private const val PARAM_OVER = "intent_param_overview"

        fun start(context: Context, overview: Overview) {

            val intent = Intent(context, OverviewActivity::class.java)
            intent.putExtra(PARAM_OVER, overview)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val overview by lazy { intent.getParcelableExtra<Overview>(PARAM_OVER)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newOverview: Overview? = null
        DoAsync(
            doWork = {
                newOverview = client.overview(overview.id, true)
            },
            onPost = {
                binding.modelText.text = newOverview.toString()
            }
        )
    }
}
