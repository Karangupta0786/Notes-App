package com.example.finalnotesapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)



        val text: TextView = findViewById(R.id.author)
        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.l_to_r_animation)
        text.startAnimation(animation)


        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this@SplashScreen,MainActivity::class.java))
            finish()
        },4000)

    }
}