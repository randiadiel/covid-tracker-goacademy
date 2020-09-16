package com.example.covidtrackergoacademy.lookup.mvp

import android.widget.Toast
import com.example.covidtrackergoacademy.lookup.LookUpData
import com.example.covidtrackergoacademy.lookup.adapter.LookUpAdapter
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

class LookUpPresenter(private val model : LookUpModel, val view : LookUpContract.View) : LookUpContract.Presenter {
    override fun getData() {
        val mockLookUpList = mutableListOf(
            LookUpData("Loading...", 0, 0, 0)
        )

        view.updateData(mockLookUpList)

        model.getData(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                view.showError(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonString: String? = response.body?.string()
                    val jsonArray = JSONArray(jsonString)

                    val data : MutableList<LookUpData> = mutableListOf<LookUpData>()

                    for (i in 0 until jsonArray.length()) {
                        data.add(
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
                    view.updateData(data)
                } catch (e: Exception) {
                    view.showError(e.toString())
                }
            }
        })
    }

}