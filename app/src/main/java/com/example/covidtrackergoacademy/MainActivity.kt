package com.example.covidtrackergoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ib_look_up.setOnClickListener() {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("texts", "This is from first activity")
            startActivity(intent)
        }
    }
}