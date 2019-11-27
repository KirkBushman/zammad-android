package com.kirkbushman.sampleapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.Object
import kotlinx.android.synthetic.main.activity_object.*

class ObjectActivity : AppCompatActivity() {

    companion object {

        private const val PARAM_OBJECT = "intent_param_object"

        fun start(context: Context, `object`: Object) {

            val intent = Intent(context, ObjectActivity::class.java)
            intent.putExtra(PARAM_OBJECT, `object`)

            context.startActivity(intent)
        }
    }

    private val client by lazy { SampleApplication.instance.getClient() }
    private val `object` by lazy { intent.getParcelableExtra(PARAM_OBJECT) as Object }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)

        var newObject: Object? = null
        doAsync(doWork = {

            newObject = client?.`object`(`object`.id)
        }, onPost = {

            object_text.text = newObject.toString()
        })
    }
}