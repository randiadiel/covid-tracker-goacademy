package com.example.covidtrackergoacademy.main.presenter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.covidtrackergoacademy.main.data.MainActivityData
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.lookup.ui.LookUpActivity
import com.example.covidtrackergoacademy.main.model.Model
import kotlinx.android.synthetic.main.about_dialog.view.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception

class MainPresenter(val model: Model, val view: MainContract.View) : MainContract.Presenter{
    override fun getData(context: Activity){
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
                        view.invisibleLoader()
                        view.updateData(data)
                    }
                } catch (e: Exception) {
                    view.showError(e.toString())
                }
            }

        })
    }

    override fun aboutDialogCall(context: Activity) {
        context.btn_main_info.setOnClickListener {
            val aboutDialog = LayoutInflater.from(context).inflate(R.layout.about_dialog, null)
            val builder = AlertDialog.Builder(context)
                .setView(aboutDialog)
            val alertDialog = builder.show()
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            aboutDialog.btn_about_cancel.setOnClickListener{
                alertDialog.dismiss()
            }
        }
    }

    override fun lookupIntent(context: Activity) {
        context.cl_main_lookup.setOnClickListener {
            val intent = Intent(context, LookUpActivity::class.java)
            context.startActivity(intent)
        }
    }

}