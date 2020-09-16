package com.example.covidtrackergoacademy

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.covidtrackergoacademy.main.data.MainActivityData
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.main.presenter.MainContract
import com.example.covidtrackergoacademy.main.presenter.MainPresenter
import com.example.covidtrackergoacademy.main.model.Model
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter: MainContract.Presenter = MainPresenter(Model(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getData(this)
        presenter.aboutDialogCall(this)
        presenter.lookupIntent(this)
    }

    override fun updateData(data: MainActivityData) {
        runOnUiThread(){
            tv_main_heading.text = data.name
            tv_main_total_cases.text = data.positiveCases
            tv_main_confirmed_cases.text = data.hospitalizedCases
            tv_main_recovered_cases.text = data.recoveredCases
            tv_main_death_cases.text = data.deathCases
        }
    }

    override fun showError(error: String) {
        runOnUiThread(){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun invisibleLoader() {
        runOnUiThread(){
            pr_confirmed.visibility = View.INVISIBLE
            pr_recovered.visibility = View.INVISIBLE
            pr_death.visibility = View.INVISIBLE
            pr_total.visibility = View.INVISIBLE
        }
    }
}