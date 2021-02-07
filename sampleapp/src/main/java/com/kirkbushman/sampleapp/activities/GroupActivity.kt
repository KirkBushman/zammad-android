package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Group
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GroupActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_GROUP = "intent_param_group"

        fun start(context: Context, group: Group) {

            val intent = Intent(context, GroupActivity::class.java)
            intent.putExtra(PARAM_GROUP, group)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val group by lazy { intent.getParcelableExtra<Group>(PARAM_GROUP)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newGroup: Group? = null
        DoAsync(
            doWork = {
                newGroup = client.group(group.id, true)
            },
            onPost = {
                binding.modelText.text = newGroup.toString()
            }
        )
    }
}
