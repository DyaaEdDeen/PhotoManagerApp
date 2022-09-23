package com.blackdiamond.myapplication.activities

import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blackdiamond.myapplication.R
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.Float.max
import java.lang.Float.min

class ImageViewActivity : AppCompatActivity() {

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    lateinit var ivPhoto: ImageView
    var mScaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        ivPhoto = findViewById(R.id.ivPhoto)
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener(mScaleFactor, ivPhoto))

        var pic = intent.getStringExtra("photo")
        if (pic != null) {
            var imgName = pic.split("/").last()
            supportActionBar?.title = imgName
            val imgFile = File(pic)
            Picasso.get().load(imgFile).into(ivPhoto)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    private class ScaleListener(var mScaleFactor: Float, var ivPhoto: ImageView) :
        ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector?): Boolean {

            if (detector != null) {
                mScaleFactor *= detector.scaleFactor
            }
            mScaleFactor = max(0.1f, min(mScaleFactor, 10.0f))

            ivPhoto.scaleX = mScaleFactor
            ivPhoto.scaleY = mScaleFactor
            return true
        }
    }
}