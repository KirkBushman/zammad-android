package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityOrganizationUpdateBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Organization
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrganizationUpdateActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_ORGANIZATION = "intent_param_organization"

        fun start(context: Context, organization: Organization) {

            val intent = Intent(context, OrganizationUpdateActivity::class.java)
            intent.putExtra(PARAM_ORGANIZATION, organization)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val organization by lazy { intent.getParcelableExtra<Organization>(PARAM_ORGANIZATION)!! }

    private lateinit var binding: ActivityOrganizationUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrganizationUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.organizationName.setText(organization.name)
        binding.organizationActive.isChecked = organization.active
        binding.organizationShared.isChecked = organization.shared
        binding.organizationNote.setText(organization.note)

        binding.bttnSubmit.setOnClickListener {

            val name = binding.organizationName.text.toString()
            val active = binding.organizationActive.isChecked
            val shared = binding.organizationShared.isChecked
            val note = binding.organizationNote.text.toString()

            DoAsync(
                doWork = {

                    client.updateOrganization(
                        id = organization.id,
                        name = name,
                        active = active,
                        shared = shared,
                        note = note
                    )
                },
                onPost = {
                    showToast("Organization Updated!", true)
                }
            )
        }
    }
}
