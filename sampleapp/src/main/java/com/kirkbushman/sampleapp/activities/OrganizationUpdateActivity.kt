package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.showToast
import com.kirkbushman.zammad.models.Organization
import kotlinx.android.synthetic.main.activity_organization_update.*

class OrganizationUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_ORGANIZATION = "intent_param_organization"

        fun start(context: Context, organization: Organization) {

            val intent = Intent(context, OrganizationUpdateActivity::class.java)
            intent.putExtra(PARAM_ORGANIZATION, organization)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val organization by lazy { intent.getParcelableExtra<Organization>(PARAM_ORGANIZATION)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organization_update)

        organization_name.setText(organization.name)
        organization_active.isChecked = organization.active
        organization_shared.isChecked = organization.shared
        organization_note.setText(organization.note)

        bttn_submit.setOnClickListener {

            val name = organization_name.text.toString()
            val active = organization_active.isChecked
            val shared = organization_shared.isChecked
            val note = organization_note.text.toString()

            doAsync(doWork = {

                client?.updateOrganization(
                    id = organization.id,
                    name = name,
                    active = active,
                    shared = shared,
                    note = note
                )
            }, onPost = {

                showToast("Organization Updated!", true)
            })
        }
    }
}
