package com.kirkbushman.sampleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirkbushman.sampleapp.R
import com.kirkbushman.sampleapp.SampleApplication
import com.kirkbushman.sampleapp.doAsync
import com.kirkbushman.zammad.models.User
import kotlinx.android.synthetic.main.activity_me.*

class MeActivity : AppCompatActivity() {

    private val client by lazy { SampleApplication.instance.getClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me)

        var me: User? = null
        doAsync(doWork = {

            me = client?.me()
        }, onPost = {

            me_text.text = me.toString()
        })
    }
}
