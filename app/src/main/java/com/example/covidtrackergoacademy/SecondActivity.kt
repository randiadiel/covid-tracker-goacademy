package com.example.covidtrackergoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // val string:String = intent.getStringExtra("texts")
        // textView.text = string

        ib_back.setOnClickListener() {
            onBackPressed()
        }
    }
}