package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.databinding.ActivityDetailBinding
import com.kirkbushman.sampleapp.DoAsync
import com.kirkbushman.zammad.ZammadClient
import com.kirkbushman.zammad.models.Object
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ObjectActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_OBJECT = "intent_param_object"

        fun start(context: Context, `object`: Object) {

            val intent = Intent(context, ObjectActivity::class.java)
            intent.putExtra(PARAM_OBJECT, `object`)

            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var client: ZammadClient

    private val `object` by lazy { intent.getParcelableExtra<Object>(PARAM_OBJECT)!! }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var newObject: Object? = null
        DoAsync(
            doWork = {
                newObject = client.`object`(`object`.id)
            },
            onPost = {
                binding.modelText.text = newObject.toString()
            }
        )
    }
}
