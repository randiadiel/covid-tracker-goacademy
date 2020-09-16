package com.example.covidtrackergoacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.covidtrackergoacademy.main.MainContract
import com.example.covidtrackergoacademy.main.MainPresenter
import com.example.covidtrackergoacademy.main.model.Model
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter: MainContract.Presenter = MainPresenter(Model(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getData(this)
        presenter.aboutDialogCall(this)
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
}