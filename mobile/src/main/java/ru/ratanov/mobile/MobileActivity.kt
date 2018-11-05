package ru.ratanov.mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController

class MobileActivity : AppCompatActivity(), NavHost {
    override fun getNavController(): NavController {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment).navigateUp()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        //todo Check if start screen -> exit
//        finishAffinity()
//        System.exit(0)
    }





}
