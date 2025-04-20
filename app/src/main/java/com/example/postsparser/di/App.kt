package com.example.postsparser.di

import android.app.Application

class App : Application() {


    val component by lazy {
        DaggerAppComponent.create()
    }

}