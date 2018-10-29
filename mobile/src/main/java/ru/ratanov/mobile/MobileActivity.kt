package ru.ratanov.mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController

class MobileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.content, TopFragment.newInstance())
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        //todo Check if start screen -> exit
//        finishAffinity()
//        System.exit(0)
    }





}
