package com.example.myyy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        auth= FirebaseAuth.getInstance()



        if(auth.currentUser != null) {

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainscreenActivity::class.java))
                finish()
            }, 1500)

        }else{

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, secondscreen::class.java))
                finish()
            }, 1500)
        }

        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.statusBars())



    }
}