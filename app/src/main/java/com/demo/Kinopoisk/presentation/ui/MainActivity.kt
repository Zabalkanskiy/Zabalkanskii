package com.demo.Kinopoisk.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.FragmentContainerView
import com.demo.Kinopoisk.R

class MainActivity : AppCompatActivity() {
    lateinit var fragmentContainerView: FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_layout)

        fragmentContainerView = findViewById(R.id.main_activity_fragment_container)

        val fragment =
            supportFragmentManager.findFragmentById(R.id.main_activity_fragment_container)

        if (fragment == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.main_activity_fragment_container,
                PopularFragment.newInstance(),
                POPULARFRAGMENT
            )
                .commit()
        }


    }



}