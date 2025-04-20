package com.example.postsparser

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.postsparser.presentation.PostsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Запуск PostsFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container,PostsFragment.newInstance())
                .commit()
            }
        }








}