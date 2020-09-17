package com.example.covidtrackergoacademy.splash.presenter

interface SplashContract {
    interface Presenter{
        fun delayToIntent()
    }
    interface View{
        fun intentMain()
    }
}