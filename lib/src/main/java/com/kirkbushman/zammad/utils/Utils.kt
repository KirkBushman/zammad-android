package com.kirkbushman.zammad.utils

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun convertStringToDate(dateStr: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return format.parse(dateStr.replace('T', ' ').replace('Z', ' '))
    }

    fun buildRetrofit(baseUrl: String, logging: Boolean): Retrofit {

        val moshi = Moshi.Builder().build()
        val moshiFactory = MoshiConverterFactory.create(moshi)

        val httpClient = if (logging) {

            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
        } else {

            OkHttpClient.Builder()
                .build()
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshiFactory)
            .client(httpClient)
            .build()
    }
}
