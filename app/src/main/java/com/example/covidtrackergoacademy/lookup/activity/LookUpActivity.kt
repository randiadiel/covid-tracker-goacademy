package com.example.covidtrackergoacademy.lookup.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.lookup.LookUpData
import com.example.covidtrackergoacademy.lookup.adapter.LookUpAdapter
import com.example.covidtrackergoacademy.lookup.mvp.LookUpContract
import com.example.covidtrackergoacademy.lookup.mvp.LookUpModel
import com.example.covidtrackergoacademy.lookup.mvp.LookUpPresenter
import kotlinx.android.synthetic.main.activity_look_up.*
import java.util.*

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

        ib_back.setOnClickListener {
            onBackPressed()
        }

        ib_cancel.setOnClickListener {
            et_look_up.setText("")
        }

        et_look_up.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(et_look_up.text.toString() != "") {
                    ib_cancel.visibility = View.VISIBLE
                }
                else {
                    ib_cancel.visibility = View.INVISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {
                filteredList.clear()
                mockLookUpList.forEach {
                    if (it.provinceName.toLowerCase(Locale.getDefault()).contains(s.toString().toLowerCase())) {
                        filteredList.add(it)
                    }
                }
                val lookUpAdapterUpdate = LookUpAdapter(filteredList)
                rv_look_up.adapter = lookUpAdapterUpdate
                rv_look_up.adapter!!.notifyDataSetChanged()
            }
        })
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