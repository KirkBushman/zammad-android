package com.kirkbushman.sampleapp

import android.app.Application
import com.kirkbushman.sampleapp.data.Preferences
import com.kirkbushman.zammad.ZammadClient

class SampleApplication : Application() {

    companion object {
        lateinit var instance: SampleApplication
    }

    init {
        instance = this
    }

    private var client: ZammadClient? = null
    fun getClient(): ZammadClient? {
        return client
    }
    fun setClient(client: ZammadClient) {
        this.client = client
    }

    val prefs by lazy { Preferences(this) }
}
