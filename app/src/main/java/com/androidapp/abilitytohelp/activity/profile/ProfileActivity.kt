package com.androidapp.abilitytohelp.activity.profile

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.androidapp.abilitytohelp.R

class ProfileActivity : AppCompatActivity() {

    var logout: Button? = null
    var deleteAccount: Button? = null
    var profileImage: ImageView? = null
    var fullName: EditText? = null
    var schoolName: EditText? = null
    var age: EditText? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

        logout = findViewById(R.id.logoutBtn)
        deleteAccount = findViewById(R.id.deleteAccout)
        profileImage = findViewById(R.id.profileImage)
        fullName = findViewById(R.id.name)
        schoolName = findViewById(R.id.schoolName)
        age = findViewById(R.id.age)


        profileImage?.setOnClickListener {

        }
        deleteAccount?.setOnClickListener {

        }
        logout?.setOnClickListener {

        }
    }
}