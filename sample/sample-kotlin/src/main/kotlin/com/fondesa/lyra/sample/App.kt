package com.fondesa.lyra.sample

import android.app.Application
import android.os.Build

import com.fondesa.lyra.Lyra
import com.fondesa.lyra.coder.gson.DefaultGsonCoderRetriever
import com.fondesa.lyra.field.DefaultFieldsRetriever

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val builder = Lyra.with(this)
                .coderRetriever(DefaultGsonCoderRetriever())
                .fieldsRetriever(DefaultFieldsRetriever())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            builder.autoSaveActivities()
        }
        builder.build()
    }
}