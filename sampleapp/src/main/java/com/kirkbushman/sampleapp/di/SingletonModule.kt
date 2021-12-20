package com.kirkbushman.sampleapp.di

import android.content.Context
import com.kirkbushman.sampleapp.data.Preferences
import com.kirkbushman.zammad.ZammadClient
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    @Reusable
    fun providePreferences(

        @ApplicationContext context: Context
    ): Preferences {

        return Preferences(context)
    }

    @Volatile
    private var mClient: ZammadClient? = null

    @JvmStatic
    @Synchronized
    fun setClient(client: ZammadClient) {
        return synchronized(this) {

            if (mClient == null) {
                mClient = client
            }
        }
    }

    @Provides
    @Singleton
    fun provideClient(

        prefs: Preferences
    ): ZammadClient {

        if (mClient == null) {
            mClient = ZammadClient(prefs.getBaseUrl(), prefs.getUsername(), prefs.getPassword(), true, true)
        }

        return mClient!!
    }
}
