package com.example.covidtrackergoacademy.lookup.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.lookup.data.LookUpData
import com.example.covidtrackergoacademy.lookup.adapter.LookUpAdapter
import com.example.covidtrackergoacademy.lookup.mvp.LookUpContract
import com.example.covidtrackergoacademy.lookup.mvp.LookUpModel
import com.example.covidtrackergoacademy.lookup.mvp.LookUpPresenter
import kotlinx.android.synthetic.main.activity_look_up.*

class LookUpActivity : AppCompatActivity(), LookUpContract.View {

    private val mockLookUpList = mutableListOf(
        LookUpData("Loading...", 0, 0, 0)
    )

    private val filteredList = mutableListOf(
        LookUpData("Loading...", 0, 0, 0)
    )

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
            val lookUpAdapter = LookUpAdapter(mockLookUpList)

            rv_look_up.layoutManager = LinearLayoutManager(this)
            rv_look_up.adapter = lookUpAdapter

            lookUpAdapter.updateData(data)
        }
    }

    override fun showError(error: String) {
        runOnUiThread {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}