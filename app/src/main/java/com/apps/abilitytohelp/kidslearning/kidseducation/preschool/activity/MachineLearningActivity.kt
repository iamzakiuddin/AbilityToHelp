package com.apps.abilitytohelp.kidslearning.kidseducation.preschool.activity

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.R
import com.apps.abilitytohelp.kidslearning.kidseducation.preschool.utils.Utils
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import java.io.IOException
import java.io.OutputStream
import java.security.AccessController.getContext
import java.util.UUID


class MachineLearningActivity : AppCompatActivity() {


    var camera : ImageView? = null
    var gallery : ImageView? = null
    var back : ImageView? = null
    var info : TextView? = null
    var bitmapImg : Bitmap? = null

    var contentImage : ImageView? = null
    var image_uri : Uri? = null

    private val IMAGE_CAPTURE_CODE: Int = 654
    private val RESULT_LOAD_IMAGE: Int = 123
    var imageLabeler: ImageLabeler? = null
    var llAdView: RelativeLayout? = null
    var llAdViewFacebook: LinearLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machine_learning)

        initUI()
        initMLKit()
        setListeners()
    }

    private fun setListeners() {
        back?.setOnClickListener {
            onBackPressed()
        }

        camera?.setOnClickListener {
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && Build.VERSION.SDK_INT<= Build.VERSION_CODES.P){
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    openCamera()
                }else{
                    val permission = arrayOf<String>(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, IMAGE_CAPTURE_CODE)
                }
            }else{
                openCamera()
            }
        }

        gallery?.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE)
    }

    private fun openCamera() {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE,UUID.randomUUID().toString())
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"From the camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri)
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE)
    }

    private fun initUI() {
        supportActionBar?.hide()
        camera = findViewById(R.id.camera)
        gallery = findViewById(R.id.gallery)
        back = findViewById(R.id.back)
        info = findViewById(R.id.info)
        contentImage = findViewById(R.id.content)
        llAdView = findViewById(R.id.llAdView)
        llAdViewFacebook = findViewById(R.id.llAdViewFacebook)
        Utils.loadBannerAd(this, llAdView, llAdViewFacebook)
    }

    private fun initMLKit() {
        var imageLabelerOptions = ImageLabelerOptions.Builder().build()
        imageLabeler = ImageLabeling.getClient(imageLabelerOptions)
    }


    private fun loadImage(bitmapImg: Bitmap?) {
        var inputImage: InputImage? = null
        if (bitmapImg != null) {
            inputImage = InputImage.fromBitmap(bitmapImg,0)
        }
        if (inputImage!=null){
            imageLabeler?.process(inputImage)
                ?.addOnSuccessListener {
                    info?.text = ""
                    for (label in it){
                        var imageTitle = label.text
                        var confidence = label.confidence
                        info?.append("$imageTitle: $confidence\n")
                    }
                }
                ?.addOnFailureListener{
                    info?.text = ""
                    Toast.makeText(this,"Failed to load ${it.localizedMessage}", Toast.LENGTH_LONG).show()
                }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == IMAGE_CAPTURE_CODE){
            if (permissions[0].equals(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                if (grantResults[0].equals(PackageManager.PERMISSION_GRANTED)){
                    openCamera()
                }
            }
        } else if (requestCode == RESULT_LOAD_IMAGE){
            if (permissions[1].equals(android.Manifest.permission.READ_MEDIA_IMAGES)){
                openGallery()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === IMAGE_CAPTURE_CODE && resultCode === RESULT_OK) {
            if(image_uri!=null){
                val map = getBitmapFromUri(image_uri)
                contentImage?.setImageBitmap(map)
                if(map!=null){
                    loadImage(map)
                }
            }
        }

        if (requestCode === RESULT_LOAD_IMAGE && resultCode === RESULT_OK && android.R.attr.data != null) {
            image_uri = data?.data
            val bitmap = getBitmapFromUri(image_uri!!)
            contentImage?.setImageBitmap(bitmap)
            loadImage(bitmap)
        }
    }

    private fun getBitmapFromUri(uri: Uri?): Bitmap? {
        return try {
            val inputStream = uri?.let { contentResolver.openInputStream(it) }
            val generatedBitmap = BitmapFactory.decodeStream(inputStream)
            //val matrix = Matrix()
            //matrix.postRotate(90F)
            //Bitmap.createBitmap(generatedBitmap, 0, 0, generatedBitmap.width, generatedBitmap.height, matrix, true)
            ImageRotation.fixBitmapOrientation(uri,generatedBitmap,this)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right,R.anim.right_to_left)
    }
}