package com.example.myyy

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myyy.databinding.ActivityProfileBinding
import com.example.myyy.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.Date

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var database :FirebaseDatabase
    private lateinit var storage : FirebaseStorage
    private lateinit var selectedImg : Uri
    private lateinit var dialog : AlertDialog


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val builder = AlertDialog.Builder(this)

            builder.setTitle("Updating Profle")
            builder.setMessage("Please wait...")
            builder.setCancelable(false)

        dialog = builder.create()


        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()

        binding.userImage.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"

            startActivityForResult(intent, 1)
        }

        binding.continuenext.setOnClickListener {
            if(binding.userName.text!!.isEmpty()){
                Toast.makeText(this, "Please enter your Name..", Toast.LENGTH_SHORT).show()
            }else if(selectedImg == null){
                Toast.makeText(this, "Please select your Image", Toast.LENGTH_SHORT).show()
            }else{
                dialog.show()
                uploadData()
            }

        }

    }
        private fun uploadInfo(imgUrl: String) {
            val user = UserModel(auth.uid.toString(), binding.userName.text.toString(), auth.currentUser!!.phoneNumber.toString(), imgUrl)

            database.reference.child("users")
                .child(auth.uid.toString())
                .setValue(user)
                .addOnSuccessListener {
                    dialog.dismiss()
                    Toast.makeText(this, "Data inserted Successfully!!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainscreenActivity::class.java)
                    intent.putExtra("name_user",binding.userName.text!!.toString())
                    startActivity(intent)
                    finish()
                }
        }

        private  fun uploadData() {
            val reference = storage.reference.child("Profile").child(Date().time.toString())
            reference.putFile(selectedImg).addOnCompleteListener{
                if(it.isSuccessful){
                    reference.downloadUrl.addOnSuccessListener { task ->
                        uploadInfo(task.toString())
                    }
                }
            }
        }


          override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (data != null) {

                if (data.data != null) {
                    selectedImg = data.data!!

                    binding.userImage.setImageURI(selectedImg)
                }
            }
        }

    }






































