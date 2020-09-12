package com.example.covidtrackergoacademy.lookup.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.lookup.LookUpData
import com.example.covidtrackergoacademy.lookup.adapter.LookUpAdapter
import kotlinx.android.synthetic.main.activity_look_up.*
import kotlinx.android.synthetic.main.activity_look_up.ib_back
import kotlinx.android.synthetic.main.activity_look_up.rv_look_up
import kotlinx.android.synthetic.main.activity_second.*

class LookUpActivity : AppCompatActivity() {

    private val mockLockUpList = mutableListOf(
        LookUpData("DKI Jakarta", 1),
        LookUpData("Sumatera", 3),
        LookUpData("Pekanbaru", 5)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_up)

        val lookUpAdapter = LookUpAdapter(mockLockUpList)

        rv_look_up.layoutManager = LinearLayoutManager(this)
        rv_look_up.adapter = lookUpAdapter

        ib_back.setOnClickListener() {
            onBackPressed()
        }
    }
}