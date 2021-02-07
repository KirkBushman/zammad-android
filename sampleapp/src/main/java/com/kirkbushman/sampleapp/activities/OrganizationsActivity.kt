package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.callbacks.OnUpDelCallback
import com.kirkbushman.sampleapp.controllers.OrganizationsController
import com.kirkbushman.sampleapp.databinding.ActivityOrganizationsBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.sampleapp.utils.showToast
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Organization
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrganizationsActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private val organizations = ArrayList<Organization>()
    private val controller by lazy {
        OrganizationsController(object : OnUpDelCallback {

            override fun onClick(position: Int) {

                val organization = organizations[position]
                OrganizationActivity.start(this@OrganizationsActivity, organization)
            }

            override fun onUpdateClick(position: Int) {

                val organization = organizations[position]
                OrganizationUpdateActivity.start(this@OrganizationsActivity, organization)
            }

            override fun onDeleteClick(position: Int) {

                DoAsync(
                    doWork = {

                        val organization = organizations[position]
                        client.deleteOrganization(organization)
                    },
                    onPost = {
                        showToast("Organization deleted!")
                    }
                )
            }
        })
    }

    private lateinit var binding: ActivityOrganizationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrganizationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.list.setHasFixedSize(true)
        binding.list.setController(controller)

        DoAsync(
            doWork = {
                organizations.addAll(client.organizations() ?: listOf())
            },
            onPost = {
                controller.setItems(organizations)
            }
        )
    }
}
