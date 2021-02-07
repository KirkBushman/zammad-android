package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Role
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoleActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_ROLE = "intent_param_role"

        fun start(context: Context, role: Role) {

            val intent = Intent(context, RoleActivity::class.java)
            intent.putExtra(PARAM_ROLE, role)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val role by lazy { intent.getParcelableExtra<Role>(PARAM_ROLE)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newRole: Role? = null
        DoAsync(
            doWork = {
                newRole = client.role(role.id, true)
            },
            onPost = {
                binding.modelText.text = newRole.toString()
            }
        )
    }
}
