package com.example.covidtrackergoacademy.hotline.presenter

import android.app.Activity
import android.view.View
import androidx.core.content.ContextCompat
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.hotline.data.HotlineData
import com.example.covidtrackergoacademy.hotline.model.HotlineModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
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

    override fun bottomSheetOpener(context: Activity) {
        context.cl_main_hotline.setOnClickListener{
            val bottomSheet = context.findViewById<View>(R.id.cl_bottom_sheet)
            val bottomSheetBehavior : BottomSheetBehavior<View> = BottomSheetBehavior.from(bottomSheet)
            val viewBg = context.findViewById<View>(R.id.bg)
            bottomSheetBehavior.addBottomSheetCallback(getBottomSheetCallback())
            if(bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED){
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED;
                viewBg.visibility = View.VISIBLE
            }else{
                view.closeBottomSheet()
            }
            viewBg.setOnClickListener() {
                view.closeBottomSheet()
            }
        }
    }

    private fun getBottomSheetCallback() : BottomSheetBehavior.BottomSheetCallback {
        return object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset == 0f) view.closeBottomSheet()
                else if (slideOffset == 1f) view.changeBackgroundToRectangle(true)
                else view.changeBackgroundToRectangle(false)
            }
        }
    }

}