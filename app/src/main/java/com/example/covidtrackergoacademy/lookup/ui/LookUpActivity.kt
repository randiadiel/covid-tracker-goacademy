package com.example.covidtrackergoacademy.lookup.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.lookup.data.LookUpData
import com.example.covidtrackergoacademy.lookup.adapter.LookUpAdapter
import com.example.covidtrackergoacademy.lookup.presenter.LookUpContract
import com.example.covidtrackergoacademy.lookup.model.LookUpModel
import com.example.covidtrackergoacademy.lookup.presenter.LookUpPresenter
import kotlinx.android.synthetic.main.activity_look_up.*

class LookUpActivity : AppCompatActivity(), LookUpContract.View {

    private val presenter : LookUpContract.Presenter = LookUpPresenter(LookUpModel(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_up)
        presenter.getData()
        presenter.search(et_look_up, ib_cancel, rv_look_up)
        presenter.backButton(this, ib_back)
    }

    override fun updateData(data: MutableList<LookUpData>) {
        runOnUiThread {
            val lookUpAdapter = LookUpAdapter(data)

            rv_look_up.layoutManager = LinearLayoutManager(this)
            rv_look_up.adapter = lookUpAdapter

        }
    }

    override fun showError(error: String) {
        runOnUiThread {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}