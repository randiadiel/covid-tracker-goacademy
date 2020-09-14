package com.example.covidtrackergoacademy.lookup.viewmodel

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.lookup.LookUpData
import com.example.covidtrackergoacademy.lookup.adapter.LookUpAdapter
import kotlinx.android.synthetic.main.activity_look_up.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class LookUpActivity : AppCompatActivity() {
    private val okHttpClient = OkHttpClient()

    private val mockLookUpList = mutableListOf(
        LookUpData("Loading...", 0, 0, 0)
    )

    private val filteredList = mutableListOf(
        LookUpData("Loading...", 0, 0, 0)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_look_up)

        val lookUpAdapter = LookUpAdapter(mockLookUpList)

        rv_look_up.layoutManager = LinearLayoutManager(this)
        rv_look_up.adapter = lookUpAdapter

        val request = Request.Builder()
            .url("https://api.kawalcorona.com/indonesia/provinsi/")
            .build()

        okHttpClient
            .newCall(request)
            .enqueue(getCallback(lookUpAdapter))

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

    private fun getCallback(lookUpAdapter: LookUpAdapter): Callback {
        return object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                this@LookUpActivity.runOnUiThread {
                    Toast.makeText(this@LookUpActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonString: String? = response.body?.string()
                    val jsonArray = JSONArray(jsonString)

                    val lookUpListFromNetwork = mutableListOf<LookUpData>()

                    for (i in 0 until jsonArray.length()) {
                        lookUpListFromNetwork.add(
                            LookUpData(
                                provinceName = jsonArray.getJSONObject(i)
                                    .getJSONObject("attributes").getString("Provinsi"),
                                numberOfPositiveCase = jsonArray.getJSONObject(i)
                                    .getJSONObject("attributes").getInt("Kasus_Posi"),
                                numberOfRecoveredCase = jsonArray.getJSONObject(i)
                                    .getJSONObject("attributes").getInt("Kasus_Semb"),
                                numberOfDeathCase = jsonArray.getJSONObject(i)
                                    .getJSONObject("attributes").getInt("Kasus_Meni")
                            )
                        )
                    }

                    this@LookUpActivity.runOnUiThread {
                        lookUpAdapter.updateData(lookUpListFromNetwork)
                    }
                } catch (e: Exception) {
                    this@LookUpActivity.runOnUiThread {
                        Toast.makeText(this@LookUpActivity, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}