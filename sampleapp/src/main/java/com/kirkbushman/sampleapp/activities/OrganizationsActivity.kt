package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.controllers.OnUpDelCallback
import com.kirkbushman.sampleapp.controllers.OrganizationsController
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.sampleapp.showToast
import com.kirkbushman.zammad.models.Organization
import kotlinx.android.synthetic.main.activity_groups.*

class OrganizationsActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

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

                doAsync(doWork = {

                    val organization = organizations[position]
                    client?.deleteOrganization(organization)
                }, onPost = {

                    showToast("Organization deleted!")
                })
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organizations)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        list.setHasFixedSize(true)
        list.setController(controller)

        doAsync(doWork = {

            organizations.addAll(client?.organizations() ?: listOf())
        }, onPost = {

            controller.setItems(organizations)
        })
    }
}
