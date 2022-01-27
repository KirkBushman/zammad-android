package com.kirkbushman.sampleapp

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.data.Preferences
import com.kirkbushman.sampleapp.databinding.ActivityLoginBinding
import com.kirkbushman.sampleapp.di.SingletonModule
import com.kirkbushman.zammad.ZammadClient
import dagger.hilt.android.AndroidEntryPoint
import java.net.HttpURLConnection
import java.net.URL
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

            SingletonModule.setClient(ZammadClient(prefs.getBaseUrl(), prefs.getUsername(), prefs.getPassword(), true ))

            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.bttnSubmit.setOnClickListener {

            val baseUrl = binding.baseurlEdit.text.trim().toString()
            val username = binding.emailEdit.text.trim().toString()
            val password = binding.passwordEdit.text.trim().toString()

            if (!baseUrl.contains("https://")) {
                Toast.makeText(this, "baseurl not found!", Toast.LENGTH_SHORT).show()
            }

            if (username == "") {
                Toast.makeText(this, "username not found!", Toast.LENGTH_SHORT).show()
            }

            if (password == "") {
                Toast.makeText(this, "password not found!", Toast.LENGTH_SHORT).show()
            }

            else {
                var logText: String?
                var isReachable:Int?=null
                DoAsync(
                    doWork = {
                        kotlin.runCatching {
                            val userpass = "$username:$password"
                            val basicAuth = "Basic " + Base64.encodeToString(
                                userpass.toByteArray(),
                                Base64.NO_WRAP
                            )

                            isReachable =
                                if (android.os.Build.VERSION.SDK_INT >= 25) {
                                    val connection: HttpURLConnection = URL(
                                        baseUrl + "/api/v1/users/me"
                                    ).openConnection() as HttpURLConnection
                                    connection.setRequestProperty("Authorization", basicAuth);
                                    connection.requestMethod = "GET"
                                    connection.responseCode
                                } else 200
                        }
                    },
                    onPost = {
                        if (isReachable != 200) {
                            Toast.makeText(
                                this,
                                getString(R.string.inaccessible_error) + "\nERROR: " + isReachable.toString(),
                                Toast.LENGTH_LONG
                            )
                                .show()
                            return@DoAsync
                        }
                        logText = if (isReachable == 401) getString(R.string.unauthorized_error) else getString(
                            R.string.inaccessible_error
                        )

                        if (isReachable == 200) {

                            SingletonModule.setClient(
                                ZammadClient(
                                    baseUrl,
                                    username,
                                    password,
                                    true
                                )
                            )

                            with(prefs) {
                                setIsLoggedIn(true)
                                setBaseUrl(baseUrl)
                                setUsername(username)
                                setPassword(password)
                            }

                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            Toast.makeText(this, logText, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                )
            }
        }
    }
}
