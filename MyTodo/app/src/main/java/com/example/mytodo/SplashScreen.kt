package com.example.mytodo

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.room.Room
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        GlobalScope.launch {
            DataObject.listdata=database.dao().getTasks() as MutableList<CardInfo>
        }

        val appname: TextView = findViewById(R.id.appname)
        val lottie: LottieAnimationView = findViewById(R.id.lottie)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            appname.animate().translationZ(-1400F).setDuration(2700).setStartDelay(0)
        } else {
            appname.animate().translationY(-1400F).setDuration(2700).setStartDelay(0)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}