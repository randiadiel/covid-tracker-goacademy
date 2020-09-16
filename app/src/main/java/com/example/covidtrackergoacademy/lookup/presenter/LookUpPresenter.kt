package com.example.covidtrackergoacademy.lookup.presenter

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackergoacademy.lookup.data.LookUpData
import com.example.covidtrackergoacademy.lookup.adapter.LookUpAdapter
import com.example.covidtrackergoacademy.lookup.model.LookUpModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException
import java.util.*

class LookUpPresenter(private val model : LookUpModel, val view : LookUpContract.View) :
    LookUpContract.Presenter {

    private val mockLookUpList = mutableListOf(
        LookUpData("Loading...", 0, 0, 0)
    )

    override fun getData() {

        view.updateData(mockLookUpList)

        model.getData(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                view.showError(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonString: String? = response.body?.string()
                    val jsonArray = JSONArray(jsonString)

                    mockLookUpList.clear()

                    for (i in 0 until jsonArray.length()) {
                        mockLookUpList.add(
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
                    view.updateData(mockLookUpList)
                } catch (e: Exception) {
                    view.showError(e.toString())
                }
            }
        })
    }

    override fun search(et: EditText, cancelButton: ImageButton, rv: RecyclerView) {

        cancelButton.setOnClickListener {
            et.setText("")
        }

        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(et.text.toString() != "") {
                    cancelButton.visibility = View.VISIBLE
                }
                else {
                    cancelButton.visibility = View.INVISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {



                val filteredList = mockLookUpList.filter {
                    it.provinceName.toLowerCase(Locale.getDefault()).contains(s.toString().toLowerCase())
                }

                view.updateData(filteredList as MutableList<LookUpData>)

                val lookUpAdapterUpdate = LookUpAdapter(filteredList)
                rv.adapter = lookUpAdapterUpdate
                rv.adapter!!.notifyDataSetChanged()
            }
        })
    }

    override fun backButton(context : Activity, ib: ImageButton) {
        ib.setOnClickListener {
            context.onBackPressed()
        }
    }

}