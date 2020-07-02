package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Organization
import kotlinx.android.synthetic.main.activity_detail.*

class OrganizationActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_ORGAN = "intent_param_organization"

        fun start(context: Context, organization: Organization) {

            val intent = Intent(context, OrganizationActivity::class.java)
            intent.putExtra(PARAM_ORGAN, organization)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val organization by lazy { intent.getParcelableExtra(PARAM_ORGAN) as Organization }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var newOrganization: Organization? = null
        doAsync(
            doWork = {
                newOrganization = client?.organization(organization.id, true)
            },
            onPost = {
                model_text.text = newOrganization.toString()
            }
        )
    }
}
