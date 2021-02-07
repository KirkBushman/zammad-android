package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityGroupUpdateBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Group
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GroupUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_GROUP = "intent_param_group"

        fun start(context: Context, group: Group) {

            val intent = Intent(context, GroupUpdateActivity::class.java)
            intent.putExtra(PARAM_GROUP, group)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val group by lazy { intent.getParcelableExtra<Group>(PARAM_GROUP)!! }

    private lateinit var binding: ActivityGroupUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGroupUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.groupName.setText(group.name)
        binding.groupActive.isChecked = group.active
        binding.groupNote.setText(group.note)

        binding.bttnSubmit.setOnClickListener {

            val name = binding.groupName.text.toString()
            val active = binding.groupActive.isChecked
            val note = binding.groupNote.text.toString()

            DoAsync(
                doWork = {

                    client.updateGroup(
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
