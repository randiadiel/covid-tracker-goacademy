package com.example.covidtrackergoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidtrackergoacademy.lookup.ui.LookUpActivity
import com.example.covidtrackergoacademy.main.presenter.MainContract
import com.example.covidtrackergoacademy.main.presenter.MainPresenter
import com.example.covidtrackergoacademy.main.ui.MainActivity
import com.example.covidtrackergoacademy.splash.presenter.SplashContract
import com.example.covidtrackergoacademy.splash.presenter.SplashPresenter

class SplashScreen : AppCompatActivity(),SplashContract.View {
    val presenter : SplashContract.Presenter = SplashPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        presenter.delayToIntent()
    }

    override fun intentMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}