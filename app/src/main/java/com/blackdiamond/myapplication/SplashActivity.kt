package com.blackdiamond.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SplashActivity : AppCompatActivity() {

    private var splashTimeOut = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                if (!checkSelfPermission()) {
                    requestPermission()
                } else {
                    goToMainActivity()
                }
            }, splashTimeOut.toLong()
        )
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 9999
        )
    }

    private fun checkSelfPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 9999){
            if(grantResults.isNotEmpty()){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    goToMainActivity()
                }else{
                    Toast.makeText(this,"Permission was denied, cant load the images!",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun goToMainActivity() {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}