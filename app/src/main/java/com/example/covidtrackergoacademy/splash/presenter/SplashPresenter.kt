package com.example.covidtrackergoacademy.splash.presenter


import java.util.*

class SplashPresenter (val view: SplashContract.View) : SplashContract.Presenter {
    override fun delayToIntent() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                view.intentMain()
            }
        }, 2000)
    }
}