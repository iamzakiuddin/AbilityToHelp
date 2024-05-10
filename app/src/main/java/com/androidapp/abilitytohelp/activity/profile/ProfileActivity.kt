package com.androidapp.abilitytohelp.activity.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.abilitytohelp.R
import com.parse.ParseException
import com.parse.ParseFile
import com.parse.ParseUser


class ProfileActivity : AppCompatActivity() {

    var logout: Button? = null
    var deleteAccount: Button? = null
    var profileImage: ImageView? = null
    var fullName: EditText? = null
    var schoolName: EditText? = null
    var age_et: EditText? = null
    var saveBtn : RelativeLayout? = null
    var loading :  ProgressBar? = null

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
        age_et = findViewById(R.id.age)
        saveBtn = findViewById(R.id.saveBtn)
        loading = findViewById(R.id.loading)

        fetchCurrentUserDetails()

        profileImage?.setOnClickListener {

        }
        deleteAccount?.setOnClickListener {

        }
        logout?.setOnClickListener {
            logoutUser()
        }

        saveBtn?.setOnClickListener {
            updateCurrentUser()
        }
    }

    fun fetchCurrentUserDetails() {
        val query = ParseUser.getQuery()
        query.whereEqualTo("email", ParseUser.getCurrentUser().email)
        query.findInBackground { objects, e ->
            if (e != null) {
                System.out.println("Error fetching user: " + e.message)
            } else if (objects != null && objects.size > 0) {
                // Successfully fetched users
                val user = objects[0]
                val username = user.getString("name")
                val age = user.getString("age")
                val school = user.getString("school")
                fullName?.setText(username ?: "Unknown Username")
                age_et?.setText(age ?: "Unknown Age")
                schoolName?.setText(school ?: "Unknown School")

                // Load user's avatar
                val avatarFile = user.getParseFile("avatar")
                avatarFile?.getDataInBackground { data: ByteArray?, e1: ParseException? ->
                    if (e1 != null) {
                        println("Error fetching avatar data: " + e1.message)
                    } else if (data != null) {
                        // Successfully fetched avatar data
                        val bitmap =
                            BitmapFactory.decodeByteArray(data, 0, data.size)
                        //myMemojiView.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }

    fun updateCurrentUser() {
        currentFocus?.let { hideKeyboardFrom(this, it) }
        val currentUser = ParseUser.getCurrentUser()
        if (currentUser != null) {
            loading?.visibility = View.VISIBLE
            //val imageData: ByteArray = convertImageToByteArray(selectedImage) // Implement this method to convert UIImage to byte array

                //val file = ParseFile("image.jpg", imageData)
                //currentUser.put("avatar", file)
                currentUser.put("name", fullName?.text.toString())
                currentUser.put("school", schoolName?.text.toString())
                currentUser.put("age", age_et?.text.toString())
                currentUser.saveInBackground { e: ParseException? ->
                    if (e != null) {
                        loading?.visibility = View.GONE
                        println("Error uploading profile image: " + e.message)
                        simpleAlert(e.message)
                    } else {
                        loading?.visibility = View.GONE
                        println("Profile image uploaded successfully!")
                        // Dismiss the current activity or dialog
                    }
                }
        }
    }

    private fun simpleAlert(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

    }

    fun logoutUser(){
        loading?.visibility = View.VISIBLE
        ParseUser.logOutInBackground {
            if (it==null){
                loading?.visibility = View.GONE
                finish()
            }else{
                loading?.visibility = View.GONE
                simpleAlert(it.message)
            }
        }
    }


    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}