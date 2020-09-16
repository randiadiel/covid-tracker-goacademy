package com.example.covidtrackergoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidtrackergoacademy.lookup.ui.LookUpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    private val okHttpClient = OkHttpClient()
//
//    private val mockHotlineList = mutableListOf(
//        HotlineData(name = "Loading...", imgIcon = "", phone = "")
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val hotlineAdapter = HotlineAdapter(mockHotlineList)
//
//        rv_look_up.adapter = hotlineAdapter
//        rv_look_up.layoutManager = LinearLayoutManager(this)
//
//        val request = Request.Builder()
//            .url("https://bncc-corona-versus.firebaseio.com/v1/hotlines.json")
//            .build()
//
//        okHttpClient
//            .newCall(request)
//            .enqueue(getCallback(hotlineAdapter))

        ib_look_up.setOnClickListener() {
            val intent = Intent(this, LookUpActivity::class.java)

            //intent.putExtra("texts", "This is from first activity")

            startActivity(intent)
        }
    }

//    private fun getCallback(hotlineAdapter : HotlineAdapter) : Callback{
//        return object : Callback{
//            override fun onFailure(call: Call, e: IOException) {
//                this@MainActivity.runOnUiThread {
//                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                try {
//                    val jsonString: String? = response.body?.string()
//                    val jsonArray = JSONArray(jsonString)
//                    val hotlineListFromNetwork = mutableListOf<HotlineData>()
//
//                    for (i in 0 until jsonArray.length()) {
//                        hotlineListFromNetwork.add(
//                            HotlineData(
//                                imgIcon = jsonArray.getJSONObject(i).getString("img_icon"),
//                                phone = jsonArray.getJSONObject(i).getString("phone"),
//                                name = jsonArray.getJSONObject(i).getString("name")
//                            )
//                        )
//                    }
//
//                    this@MainActivity.runOnUiThread {
//                        hotlineAdapter.updateData(hotlineListFromNetwork)
//                    }
//                } catch (e: Exception) {
//                    this@MainActivity.runOnUiThread {
//                        Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//    }
}