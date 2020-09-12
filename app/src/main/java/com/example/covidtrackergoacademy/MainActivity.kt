package com.example.covidtrackergoacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val okHttpClient = OkHttpClient()

    private val mockMainActivityList = mutableListOf(
        MainActivityData("Loading...", "", "", "", "")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request:Request = Request.Builder().url("https://api.kawalcorona.com/indonesia/").build()

        okHttpClient.newCall(request).enqueue(getCallBack())


    }

    fun bind(data: MainActivityData){
        tv_main_heading.text = data.name
        tv_main_confirmed_cases.text = "${data.positiveCases}"
        tv_main_recovered_cases.text = "${data.recoveredCases}"
        tv_main_death_cases.text = "${data.deathCases}"
//        tv_main_total_cases.text ="${data.positiveCases.toBigDecimal() + data.recoveredCases.toBigDecimal() + data.hospitalizedCases.toBigDecimal() + data.deathCases.toBigDecimal()}"
    }

    private fun getCallBack() : Callback{
        return object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                this@MainActivity.runOnUiThread{
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonString = response.body?.string()
                    val jsonArray = JSONArray(jsonString)

                    for(i in 0 until jsonArray.length()){
                        bind(
                            MainActivityData(
                                name = jsonArray.getJSONObject(i).getString("name"),
                                positiveCases = jsonArray.getJSONObject(i).getString("positif"),
                                recoveredCases = jsonArray.getJSONObject(i).getString("sembuh"),
                                deathCases = jsonArray.getJSONObject(i).getString("meninggal"),
                                hospitalizedCases = jsonArray.getJSONObject(i).getString("dirawat")
                        ))
                    }
                } catch (e: Exception) {
                    this@MainActivity.runOnUiThread {
                        Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}