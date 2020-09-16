package com.example.covidtrackergoacademy.hotline.presenter

import com.example.covidtrackergoacademy.hotline.data.HotlineData
import com.example.covidtrackergoacademy.hotline.model.HotlineModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception

class HotlinePresenter(private val model: HotlineModel, private val view: HotlineContract.View) : HotlineContract.Presenter {
    override fun getData() {
        model.getData(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                view.showError(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonString = response.body?.string()
                    val jsonArray = JSONArray(jsonString)
                    val hotlineListFromNetwork = mutableListOf<HotlineData>()

                    for(i in 0 until jsonArray.length()) {
                        hotlineListFromNetwork.add(
                            HotlineData(
                                imgIcon = jsonArray.getJSONObject(i).getString("img_icon"),
                                name = jsonArray.getJSONObject(i).getString("name"),
                                phone = jsonArray.getJSONObject(i).getString("phone")
                            ))
                    }
                    view.updateData(hotlineListFromNetwork)
                } catch (e: Exception){
                    view.showError(e.toString())
                }
            }

        })
    }

}