package com.example.tic_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.service.autofill.OnClickAction
import android.view.View

import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image.translationY = -1300f

        imageName.translationY= 1700f

        play.translationX= 1700f

        image.animate().translationY(0f).duration= 2000
        imageName.animate().translationY(0f).duration= 2000
        play.animate().translationX(0f).duration= 2000



        play.setOnClickListener{
            val intent = Intent(this,StartActivity::class.java)
            startActivity(intent)
        }




    }
}