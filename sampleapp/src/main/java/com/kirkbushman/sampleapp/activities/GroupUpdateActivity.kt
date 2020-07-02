package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.showToast
import com.kirkbushman.zammad.models.Group
import kotlinx.android.synthetic.main.activity_group_update.*

class GroupUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_GROUP = "intent_param_group"

        fun start(context: Context, group: Group) {

            val intent = Intent(context, GroupUpdateActivity::class.java)
            intent.putExtra(PARAM_GROUP, group)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val group by lazy { intent.getParcelableExtra<Group>(PARAM_GROUP)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_update)

        group_name.setText(group.name)
        group_active.isChecked = group.active
        group_note.setText(group.note)

        bttn_submit.setOnClickListener {

            val name = group_name.text.toString()
            val active = group_active.isChecked
            val note = group_note.text.toString()

            doAsync(
                doWork = {

                    client?.updateGroup(
                        id = group.id,
                        name = name,
                        active = active,
                        note = note
                    )
                },
                onPost = {
                    showToast("Group Updated!")
                }
            )
        }
    }
}
