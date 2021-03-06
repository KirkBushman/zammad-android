package com.kirkbushman.sampleapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.data.Preferences
import com.kirkbushman.sampleapp.databinding.ActivityLoginBinding
import com.kirkbushman.sampleapp.di.SingletonModule
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var prefs: Preferences

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (prefs.getIsLoggedIn()) {

            SingletonModule.setClient(ZammadClient(prefs.getBaseUrl(), prefs.getUsername(), prefs.getPassword(), true))

            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.bttnSubmit.setOnClickListener {

            val baseUrl = binding.baseurlEdit.text.trim().toString()
            val username = binding.emailEdit.text.trim().toString()
            val password = binding.passwordEdit.text.trim().toString()

            if (baseUrl == "") {
                Toast.makeText(this, "baseurl not found!", Toast.LENGTH_SHORT).show()
            }

            if (username == "") {
                Toast.makeText(this, "username not found!", Toast.LENGTH_SHORT).show()
            }

            if (password == "") {
                Toast.makeText(this, "password not found!", Toast.LENGTH_SHORT).show()
            }

            var me: User? = null
            DoAsync(
                doWork = {
                    me = ZammadClient.me(baseUrl, username, password, true)
                },
                onPost = {

                    if (me != null) {

                        SingletonModule.setClient(ZammadClient(baseUrl, username, password, true))

                        with(prefs) {
                            setIsLoggedIn(true)
                            setBaseUrl(baseUrl)
                            setUsername(username)
                            setPassword(password)
                        }

                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "Error while trying to login!", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}
