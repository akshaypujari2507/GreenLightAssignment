package com.greenlight.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.greenlight.R
import com.greenlight.data.remote.model.ApiResponse
import com.greenlight.di.Injection
import com.greenlight.ui.viewmodel.MainActivityViewModel
import com.greenlight.utils.Util
import kotlinx.android.synthetic.main.actionbar_view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, Injection.provideMainActivityViewModelFactory(this))
            .get(MainActivityViewModel::class.java)

        initView()

        if (Util.isNetworkAvailable(this)) {
            progressBar.visibility = View.VISIBLE
            fetchRemoteRecords()
        }
    }

    fun initView() {

        iv_backArrow.visibility = View.GONE

        btnPerformance.setOnClickListener {

            GlobalScope.launch(Dispatchers.Main) {
                val countries = viewModel.getCountry()

                if (countries.size > 0) {
                    val country = countries.get(0)?.country
                    callZoneActivity(country!!)
                } else {
                    callZoneActivity("")
//                    toast("Empty List!")
                }
            }
        }
    }

    private fun callZoneActivity(country: String) {
        val intent = Intent(this, ZoneActivity::class.java)
        intent.putExtra(Util.COUNTRY_KEY, country)
        startActivity(intent)
    }

    private fun fetchRemoteRecords() {

        viewModel.getRemoteRecord()?.observe(this, Observer<ApiResponse> {
//            toast("Result: ${it.Success}")
            progressBar.visibility = View.GONE
        })
    }

    fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}