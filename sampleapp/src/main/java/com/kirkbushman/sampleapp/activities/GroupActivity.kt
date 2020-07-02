package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Group
import kotlinx.android.synthetic.main.activity_detail.*

class GroupActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_GROUP = "intent_param_group"

        fun start(context: Context, group: Group) {

            val intent = Intent(context, GroupActivity::class.java)
            intent.putExtra(PARAM_GROUP, group)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val group by lazy { intent.getParcelableExtra(PARAM_GROUP) as Group }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var newGroup: Group? = null
        doAsync(
            doWork = {
                newGroup = client?.group(group.id, true)
            },
            onPost = {
                model_text.text = newGroup.toString()
            }
        )
    }
}
