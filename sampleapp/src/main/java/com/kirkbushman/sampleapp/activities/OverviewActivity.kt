package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Overview
import kotlinx.android.synthetic.main.activity_detail.*

class OverviewActivity : AppCompatActivity(R.layout.activity_detail) {

    companion object {

        private const val PARAM_OVER = "intent_param_overview"

        fun start(context: Context, overview: Overview) {

            val intent = Intent(context, OverviewActivity::class.java)
            intent.putExtra(PARAM_OVER, overview)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val overview by lazy { intent.getParcelableExtra<Overview>(PARAM_OVER)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var newOverview: Overview? = null
        doAsync(
            doWork = {
                newOverview = client?.overview(overview.id, true)
            },
            onPost = {
                model_text.text = newOverview.toString()
            }
        )
    }
}
