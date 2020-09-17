package com.example.covidtrackergoacademy.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtrackergoacademy.R
import com.example.covidtrackergoacademy.hotline.adapter.HotlineAdapter
import com.example.covidtrackergoacademy.hotline.data.HotlineData
import com.example.covidtrackergoacademy.hotline.model.HotlineModel
import com.example.covidtrackergoacademy.hotline.presenter.HotlineContract
import com.example.covidtrackergoacademy.hotline.presenter.HotlinePresenter
import com.example.covidtrackergoacademy.main.data.MainActivityData
import com.example.covidtrackergoacademy.main.presenter.MainContract
import com.example.covidtrackergoacademy.main.presenter.MainPresenter
import com.example.covidtrackergoacademy.main.model.Model
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View,HotlineContract.View {

    private val presenter: MainContract.Presenter = MainPresenter(Model(), this)
    private val hotlinePresenter : HotlineContract.Presenter = HotlinePresenter(HotlineModel(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getData(this)
        presenter.aboutDialogCall(this)
        presenter.lookupIntent(this)
        hotlinePresenter.getData()
        hotlinePresenter.bottomSheetOpener(this)
    }

    override fun updateData(data: MainActivityData) {
        runOnUiThread(){
            tv_main_heading.text = data.name
            tv_main_total_cases.text = data.positiveCases
            tv_main_confirmed_cases.text = data.hospitalizedCases
            tv_main_recovered_cases.text = data.recoveredCases
            tv_main_death_cases.text = data.deathCases
        }
    }

    override fun updateData(data: MutableList<HotlineData>) {
        runOnUiThread(){
            val hotlineAdapter = HotlineAdapter(data)

            rv_main_hotline.layoutManager = LinearLayoutManager(this)
            rv_main_hotline.adapter = hotlineAdapter
        }
    }

    override fun showError(error: String) {
        runOnUiThread(){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun closeBottomSheet() {
        val bottomSheet = findViewById<View>(R.id.cl_bottom_sheet)
        val bottomSheetBehavior : BottomSheetBehavior<View> = BottomSheetBehavior.from(bottomSheet)
        val viewBg = findViewById<View>(R.id.bg)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED;
        viewBg.visibility = View.INVISIBLE
    }

    override fun changeBackgroundToRectangle(set: Boolean) {
        val bottomSheet = findViewById<View>(R.id.cl_bottom_sheet)
        if(set == true){
            bottomSheet.background = ContextCompat.getDrawable(applicationContext,
                R.drawable.rectangle
            )
        }
        else {
            bottomSheet.background = ContextCompat.getDrawable(applicationContext,
                R.drawable.top_rounded
            )
        }
    }

    override fun invisibleLoader() {
        runOnUiThread(){
            pr_confirmed.visibility = View.INVISIBLE
            pr_recovered.visibility = View.INVISIBLE
            pr_death.visibility = View.INVISIBLE
            pr_total.visibility = View.INVISIBLE
        }
    }
}