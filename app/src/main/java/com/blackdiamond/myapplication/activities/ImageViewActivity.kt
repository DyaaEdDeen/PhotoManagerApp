package com.blackdiamond.myapplication.activities

import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.blackdiamond.myapplication.R
import com.squareup.picasso.Picasso
import java.io.File
import java.lang.Float.max
import java.lang.Float.min

class ImageViewActivity : AppCompatActivity() {

    private lateinit var scaleGestureDetector: ScaleGestureDetector

    lateinit var ivPhoto: ImageView
    lateinit var clParent: ConstraintLayout

    var doubleClick = false
    val doubleClickTimeOut = 500
    var x = 0.0f
    var y = 0.0f

    var mScaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        ivPhoto = findViewById(R.id.ivPhoto)
        clParent = findViewById(R.id.cdParent)
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
        if (event != null && event.pointerCount == 1) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!doubleClick) {//Single Click
                        doubleClick = true
                        Handler().postDelayed(
                            {
                                doubleClick = false
                            }, doubleClickTimeOut.toLong()
                        )
                        x = event.rawX - ivPhoto.x
                        y = event.rawY - ivPhoto.y
                    } else {//Double Click
                        ivPhoto.scaleX = 1f
                        ivPhoto.scaleY = 1f
                        ivPhoto.x = 0f
                        ivPhoto.y = 0f
                        Handler().postDelayed(
                            {
                                doubleClick = false
                            }, doubleClickTimeOut.toLong()
                        )
                    }
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    ivPhoto.animate().x(event.rawX - x).y(event.rawY - y).setDuration(0).start()
                    true
                }
                else -> {
                    true
                }
            }
        }
        return true
    }

    private class ScaleListener(var mScaleFactor: Float, var ivPhoto: ImageView) :
        ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            if (detector != null) {
                mScaleFactor *= detector.scaleFactor
            }
            mScaleFactor = max(0.1f, min(mScaleFactor, 5f))
            ivPhoto.animate().scaleX(mScaleFactor).scaleY(mScaleFactor).setDuration(0).start()
            return true
        }
    }
}