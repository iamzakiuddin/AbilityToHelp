package com.androidapp.abilitytohelp.activity.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.emoji2.emojipicker.EmojiPickerView
import com.androidapp.abilitytohelp.R
import com.parse.ParseException
import com.parse.ParseFile
import com.parse.ParseUser
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream


class ProfileActivity : AppCompatActivity() {

    private var isEmojiPickerOpen: Boolean = false
    private var imageData: ByteArray? = null
    private var emojiPickerView: EmojiPickerView? = null
    private var emojiLayout: LinearLayout? = null
    private var doneText : TextView? = null
    var logout: Button? = null
    var deleteAccount: Button? = null
    var profileImage: CircleImageView? = null
    var fullName: EditText? = null
    var schoolName: EditText? = null
    var age_et: EditText? = null
    var saveBtn: RelativeLayout? = null
    var loading: ProgressBar? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

        doneText = findViewById(R.id.done)
        logout = findViewById(R.id.logoutBtn)
        deleteAccount = findViewById(R.id.deleteAccout)
        profileImage = findViewById(R.id.profileImage)
        fullName = findViewById(R.id.name)
        schoolName = findViewById(R.id.schoolName)
        age_et = findViewById(R.id.age)
        saveBtn = findViewById(R.id.saveBtn)
        loading = findViewById(R.id.loading)
        emojiLayout = findViewById(R.id.emojiLayout)
        emojiPickerView = findViewById(R.id.emoji_picker)
        fetchCurrentUserDetails()

        emojiPickerView?.setOnEmojiPickedListener {
            convertUniCodeToImage(it.emoji)
        }
        profileImage?.setOnClickListener {
            showEmojiPicker()
        }
        deleteAccount?.setOnClickListener {
            showAlert("Delete Account", "Are you sure you want to delete this user account?")
        }
        logout?.setOnClickListener {
            logoutUser()
        }

        saveBtn?.setOnClickListener {
            if (isEmojiPickerOpen){
                hideEmojiPicker()
            }
            updateCurrentUser()
        }

        doneText?.setOnClickListener {
            hideEmojiPicker()
        }

    }

    private fun deleteUser() {
        loading?.visibility = View.VISIBLE
        val currentUser = ParseUser.getCurrentUser()
        currentUser?.deleteInBackground {
            if (it != null) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            } else {
                ParseUser.logOut()
                loading?.visibility = View.GONE
                finish()
            }
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
                        val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                        profileImage?.setImageBitmap(bitmap)
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
            if (imageData != null) {
                val file = ParseFile("image.jpg", imageData)
                currentUser.put("avatar", file)
            }
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }

    private fun showAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Delete") { dialog, which ->
                deleteUser()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }

    fun logoutUser() {
        loading?.visibility = View.VISIBLE
        ParseUser.logOutInBackground {
            if (it == null) {
                loading?.visibility = View.GONE
                finish()
            } else {
                loading?.visibility = View.GONE
                simpleAlert(it.message)
            }
        }
    }


    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun showEmojiPicker() {
        isEmojiPickerOpen = true
        emojiLayout?.visibility = View.VISIBLE
        val animate = emojiLayout?.height?.let { TranslateAnimation(0f, 0f, it.toFloat(), 0f) }
        animate?.duration = 200
        animate?.fillAfter = true
        emojiLayout?.startAnimation(animate)
    }

    private fun hideEmojiPicker() {
        isEmojiPickerOpen = false
        val animate = emojiLayout?.height?.let { TranslateAnimation(0f, 0f, 0f, it.toFloat()) }
        animate?.duration = 200
        animate?.fillAfter = true
        emojiLayout?.startAnimation(animate)
        emojiLayout?.visibility = View.GONE
    }

    fun convertUniCodeToImage(emo: String) {
        // Calculate the size of the Bitmap based on ImageView dimensions
        val imageViewWidth = 150 // width of ImageView in dp
        val imageViewHeight = 150 // height of ImageView in dp
        val scale = Resources.getSystem().displayMetrics.density
        val widthPixels = (imageViewWidth * scale + 0.5f).toInt()
        val heightPixels = (imageViewHeight * scale + 0.5f).toInt()

        // Create a Bitmap with ARGB_8888 configuration and ImageView dimensions
        val bitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888)

        // Wrap the Bitmap in a Canvas
        val canvas = Canvas(bitmap)

        // Create Paint with desired Typeface and TextSize
        val paint = Paint().apply {
            color = Color.BLACK // Set text color
            textSize = 100 * scale // Set text size based on ImageView size
            isAntiAlias = true // Enable anti-aliasing for smooth edges
        }

        // Calculate x and y coordinates to center the emoji in the ImageView
        val x = (widthPixels - paint.measureText(emo)) / 2 // Center horizontally
        val y = heightPixels / 2 + (paint.textSize / 2) // Center vertically

        // Draw text (emoji) on Canvas
        canvas.drawText(emo, x, y, paint)

        // Display the Bitmap in ImageView
        profileImage?.setImageBitmap(bitmap)
        imageData = convertToByteArray(bitmap)
    }


    fun convertToByteArray(bmp:Bitmap): ByteArray {
        val immagex: Bitmap = bmp
        val baos = ByteArrayOutputStream()
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val bytes = baos.toByteArray()
        return bytes
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (isEmojiPickerOpen){
            hideEmojiPicker()
        }
    }
}