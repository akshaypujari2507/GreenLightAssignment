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
import com.greenlight.data.local.entities.Area
import com.greenlight.di.Injection
import com.greenlight.ui.adapter.AreaAdapter
import com.greenlight.ui.interfaces.OnAreaClicked
import com.greenlight.ui.viewmodel.AreaViewModel
import com.greenlight.utils.Util
import kotlinx.android.synthetic.main.actionbar_view.*
import kotlinx.android.synthetic.main.activity_area.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AreaActivity : AppCompatActivity(), OnAreaClicked {

    private lateinit var viewModel: AreaViewModel
    private val GRID_COLUMNS_PORTRAIT = 1
    private val GRID_COLUMNS_LANDSCAPE = 2
    private lateinit var mGridLayoutManager: GridLayoutManager
    lateinit var mAdapter: AreaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area)

        val region = intent.getStringExtra(Util.REGION_KEY)

        tv_performance.text = "$region Performance"

        viewModel = ViewModelProviders.of(this, Injection.provideAreaActivityViewModelFactory(this))
            .get(AreaViewModel::class.java)

        initView()
        initRecyclerView()

        GlobalScope.launch(Dispatchers.Main) {
            fetchArea(region!!)
        }

    }

    private fun initView() {
        iv_backArrow.setOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView() {
        configureRecyclerAdapter(resources.configuration.orientation)

        mAdapter = AreaAdapter(this@AreaActivity)
        recycleView.adapter = mAdapter
    }

    private suspend fun fetchArea(region: String) {

        viewModel.getAreas("%$region%")?.observe(this, Observer<List<Area>> {
            setUpAdapter(it)
        })
    }

    private fun setUpAdapter(areas: List<Area>) {
        if (areas.size > 0) {
            mAdapter.areas = areas
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

    override fun onItemClicked(area: Area) {
        callEmployees(area.area.toString())
    }

    private fun callEmployees(area: String) {
        val intent = Intent(this, EmployeeActivity::class.java)
        intent.putExtra(Util.AREA_KEY, area)
        startActivity(intent)
    }


    fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}