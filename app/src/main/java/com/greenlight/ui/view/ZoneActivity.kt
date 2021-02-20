package com.greenlight.ui.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.greenlight.R
import com.greenlight.data.local.entities.Zone
import com.greenlight.di.Injection
import com.greenlight.ui.adapter.ZoneAdapter
import com.greenlight.ui.interfaces.OnZoneClicked
import com.greenlight.ui.viewmodel.ZoneViewModel
import com.greenlight.utils.Util
import kotlinx.android.synthetic.main.actionbar_view.*
import kotlinx.android.synthetic.main.activity_zone.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ZoneActivity : AppCompatActivity(), OnZoneClicked {

    private lateinit var viewModel: ZoneViewModel
    private val GRID_COLUMNS_PORTRAIT = 1
    private val GRID_COLUMNS_LANDSCAPE = 2
    private lateinit var mGridLayoutManager: GridLayoutManager
    lateinit var mAdapter: ZoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zone)

        val country = intent.getStringExtra(Util.COUNTRY_KEY)

        tv_performance.text = "$country Performance"

        viewModel = ViewModelProviders.of(this, Injection.provideZoneActivityViewModelFactory(this))
            .get(ZoneViewModel::class.java)

        initView()
        initRecyclerView()

        GlobalScope.launch(Dispatchers.Main) {
            fetchZone(country!!)
        }

    }

    private fun initView() {
        iv_backArrow.setOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView() {
        configureRecyclerAdapter(resources.configuration.orientation)

        mAdapter = ZoneAdapter(this@ZoneActivity)
        recycleView.adapter = mAdapter
    }

    private suspend fun fetchZone(country: String) {

        viewModel.getZones("%$country%")?.observe(this, Observer<List<Zone>> {
            setUpAdapter(it)
        })
    }

    private fun setUpAdapter(zones: List<Zone>) {
        if (zones.size > 0) {
            mAdapter.zones = zones
            cl_container.visibility = View.VISIBLE
            tv_noRecord.visibility = View.GONE
        } else {
            cl_container.visibility = View.GONE
            tv_noRecord.visibility = View.VISIBLE
            tv_noRecord.setText(resources.getString(R.string.noRecords))
        }
    }

    private fun configureRecyclerAdapter(orientation: Int) {
        val isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT
        mGridLayoutManager = GridLayoutManager(
            this,
            if (isPortrait) GRID_COLUMNS_PORTRAIT else GRID_COLUMNS_LANDSCAPE
        )
        recycleView.setLayoutManager(mGridLayoutManager)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onItemClicked(zone: Zone) {
        callRegionActivity(zone.zone.toString())
    }

    private fun callRegionActivity(zone: String) {
        val intent = Intent(this, RegionActivity::class.java)
        intent.putExtra(Util.ZONE_KEY, zone)
        startActivity(intent)
    }


    fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}