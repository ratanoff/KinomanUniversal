package ru.ratanov.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mobile.*

class MobileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        System.exit(0)
    }
}
