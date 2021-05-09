package com.example.project.presentation

import android.app.Application
import android.content.Context

class StarWarsApplication : Application() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}