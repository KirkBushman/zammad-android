package com.kirkbushman.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kirkbushman.sampleapp.activities.*
import com.kirkbushman.sampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bttnMe.setOnClickListener {

            val intent = Intent(this, MeActivity::class.java)
            startActivity(intent)
        }

        binding.bttnTickets.setOnClickListener {

            val intent = Intent(this, TicketsActivity::class.java)
            startActivity(intent)
        }

        binding.bttnTicketsSearch.setOnClickListener {

            val intent = Intent(this, ActivitySearch::class.java)
            startActivity(intent)
        }

        binding.bttnUsers.setOnClickListener {

            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }

        binding.bttnGroups.setOnClickListener {

            val intent = Intent(this, GroupsActivity::class.java)
            startActivity(intent)
        }

        binding.bttnRoles.setOnClickListener {

            val intent = Intent(this, RolesActivity::class.java)
            startActivity(intent)
        }

        binding.bttnTags.setOnClickListener {

            val intent = Intent(this, TagsActivity::class.java)
            startActivity(intent)
        }

        binding.bttnOverviews.setOnClickListener {

            val intent = Intent(this, OverviewsActivity::class.java)
            startActivity(intent)
        }

        binding.bttnOrganizations.setOnClickListener {

            val intent = Intent(this, OrganizationsActivity::class.java)
            startActivity(intent)
        }

        binding.bttnPriorities.setOnClickListener {

            val intent = Intent(this, PrioritiesActivity::class.java)
            startActivity(intent)
        }

        binding.bttnStates.setOnClickListener {

            val intent = Intent(this, StatesActivity::class.java)
            startActivity(intent)
        }

        binding.bttnObjects.setOnClickListener {

            val intent = Intent(this, ObjectsActivity::class.java)
            startActivity(intent)
        }

        binding.bttnNotifications.setOnClickListener {

            val intent = Intent(this, NotificationsActivity::class.java)
            startActivity(intent)
        }
    }
}
