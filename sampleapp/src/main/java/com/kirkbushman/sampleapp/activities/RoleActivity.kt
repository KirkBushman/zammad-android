package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Role
import kotlinx.android.synthetic.main.activity_detail.*

class RoleActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_ROLE = "intent_param_role"

        fun start(context: Context, role: Role) {

            val intent = Intent(context, RoleActivity::class.java)
            intent.putExtra(PARAM_ROLE, role)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val role by lazy { intent.getParcelableExtra(PARAM_ROLE) as Role }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var newRole: Role? = null
        doAsync(
            doWork = {
                newRole = client?.role(role.id, true)
            },
            onPost = {
                model_text.text = newRole.toString()
            }
        )
    }
}
