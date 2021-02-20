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
import com.greenlight.data.local.entities.Region
import com.greenlight.di.Injection
import com.greenlight.ui.adapter.RegionAdapter
import com.greenlight.ui.interfaces.OnRegionClicked
import com.greenlight.ui.viewmodel.RegionViewModel
import com.greenlight.utils.Util
import kotlinx.android.synthetic.main.actionbar_view.*
import kotlinx.android.synthetic.main.activity_region.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegionActivity : AppCompatActivity(), OnRegionClicked {

    private lateinit var viewModel: RegionViewModel
    private val GRID_COLUMNS_PORTRAIT = 1
    private val GRID_COLUMNS_LANDSCAPE = 2
    private lateinit var mGridLayoutManager: GridLayoutManager
    lateinit var mAdapter: RegionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region)

        val zone = intent.getStringExtra(Util.ZONE_KEY)

        tv_performance.text = "$zone Performance"

        viewModel =
            ViewModelProviders.of(this, Injection.provideRegionActivityViewModelFactory(this))
                .get(RegionViewModel::class.java)

        initView()
        initRecyclerView()

        GlobalScope.launch(Dispatchers.Main) {
            fetchRegion(zone!!)
        }

    }

    private fun initView() {
        iv_backArrow.setOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView() {
        configureRecyclerAdapter(resources.configuration.orientation)

        mAdapter = RegionAdapter(this@RegionActivity)
        recycleView.adapter = mAdapter
    }

    private suspend fun fetchRegion(zone: String) {

        viewModel.getRegions("%$zone%")?.observe(this, Observer<List<Region>> {
//            mAdapter.regions = it
//            toast("Result: ${it.get(0).zone}")
            setUpAdapter(it)
        })
    }

    private fun setUpAdapter(regions: List<Region>) {
        if (regions.size > 0) {
            mAdapter.regions = regions
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

    override fun onItemClicked(region: Region) {
        callAreaActivity(region.region.toString())
    }

    private fun callAreaActivity(region: String) {
        val intent = Intent(this, AreaActivity::class.java)
        intent.putExtra(Util.REGION_KEY, region)
        startActivity(intent)
    }


    fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}