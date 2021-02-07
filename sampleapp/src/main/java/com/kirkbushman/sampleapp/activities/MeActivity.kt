package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MeActivity : AppCompatActivity() {

    @Inject
    lateinit var client: ZammadClient

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var me: User? = null
        DoAsync(
            doWork = {
                me = client.me()
            },
            onPost = {
                binding.modelText.text = me.toString()
            }
        )
    }
}
