package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Organization
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrganizationActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_ORGAN = "intent_param_organization"

        fun start(context: Context, organization: Organization) {

            val intent = Intent(context, OrganizationActivity::class.java)
            intent.putExtra(PARAM_ORGAN, organization)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val organization by lazy { intent.getParcelableExtra<Organization>(PARAM_ORGAN)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newOrganization: Organization? = null
        DoAsync(
            doWork = {
                newOrganization = client.organization(organization.id, true)
            },
            onPost = {
                binding.modelText.text = newOrganization.toString()
            }
        )
    }
}
