package com.example.covidtrackergoacademy.main

import com.example.covidtrackergoacademy.MainActivityData
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception

class MainPresenter(val model: Model, val view: MainContract.View) : MainContract.Presenter{
    override fun getData(){
        model.getData(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                view.showError(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonString = response.body?.string()
                    val jsonArray = JSONArray(jsonString)

                    for(i in 0 until jsonArray.length()){
                           val data = MainActivityData(
                                name = jsonArray.getJSONObject(i).getString("name"),
                                positiveCases = jsonArray.getJSONObject(i).getString("positif"),
                                recoveredCases = jsonArray.getJSONObject(i).getString("sembuh"),
                                deathCases = jsonArray.getJSONObject(i).getString("meninggal"),
                                hospitalizedCases = jsonArray.getJSONObject(i).getString("dirawat")
                            )
                        view.updateData(data)
                    }
                } catch (e: Exception) {
                    view.showError(e.toString())
                }
            }

        })
    }

}