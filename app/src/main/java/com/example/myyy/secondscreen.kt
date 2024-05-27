package com.example.myyy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myyy.databinding.ActivitySecondscreenBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class secondscreen : AppCompatActivity() {
    private lateinit var binding : ActivitySecondscreenBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        binding.button.setOnClickListener {
            if(binding.phNu.text!!.isEmpty()){
                Toast.makeText(this, "Please enter your Phone number!!", Toast.LENGTH_SHORT).show()
            }else{
                var intent = Intent(this, OtpActivity::class.java)
                intent.putExtra("number", binding.phNu.text!!.toString())
                startActivity(intent)
            }
        }



    }
}