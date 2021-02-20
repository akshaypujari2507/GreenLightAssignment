package com.greenlight.ui.view

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.greenlight.R
import com.greenlight.data.local.entities.Employee
import com.greenlight.di.Injection
import com.greenlight.ui.adapter.EmployeeAdapter
import com.greenlight.ui.interfaces.OnEmployeeClicked
import com.greenlight.ui.viewmodel.EmployeeViewModel
import com.greenlight.utils.Util
import kotlinx.android.synthetic.main.actionbar_view.*
import kotlinx.android.synthetic.main.activity_employee.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EmployeeActivity : AppCompatActivity(), OnEmployeeClicked {

    private lateinit var viewModel: EmployeeViewModel
    private val GRID_COLUMNS_PORTRAIT = 1
    private val GRID_COLUMNS_LANDSCAPE = 2
    private lateinit var mGridLayoutManager: GridLayoutManager
    lateinit var mAdapter: EmployeeAdapter

    private var isAscending = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        val area = intent.getStringExtra(Util.AREA_KEY)

        tv_performance.text = "$area Performance"

        viewModel =
            ViewModelProviders.of(this, Injection.provideEmployeeActivityViewModelFactory(this))
                .get(EmployeeViewModel::class.java)

        initView()
        initRecyclerView()

        GlobalScope.launch(Dispatchers.Main) {
            fetchEmployee(area!!)
        }

    }

    private fun initView() {
        iv_backArrow.setOnClickListener {
            finish()
        }

        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                mAdapter.filter.filter(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        tv_container_title.setOnClickListener {
            mAdapter.filteredList = mAdapter.filteredList.asReversed()
            mAdapter.employees = mAdapter.employees?.asReversed()
            mAdapter.notifyDataSetChanged()

            if (isAscending) {
                tv_container_title.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_drop_up_24),
                    null
                )
                isAscending = false
            } else {
                tv_container_title.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_drop_down_24),
                    null
                )
                isAscending = true
            }

        }

    }

    private fun initRecyclerView() {
        configureRecyclerAdapter(resources.configuration.orientation)

        mAdapter = EmployeeAdapter(this@EmployeeActivity)
        recycleView.adapter = mAdapter
    }

    private suspend fun fetchEmployee(area: String) {

        viewModel.getEmployees("%$area%")?.observe(this, Observer<List<Employee>> {
            setUpAdapter(it)
        })
    }

    private fun setUpAdapter(employees: List<Employee>) {
        if (employees.size > 0) {
            mAdapter.employees = employees
            mAdapter.filteredList.addAll(employees)
            cl_container.visibility = View.VISIBLE
            et_search.visibility = View.VISIBLE
            tv_noRecord.visibility = View.GONE
        } else {
            cl_container.visibility = View.GONE
            et_search.visibility = View.GONE
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

    override fun onItemClicked(employee: Employee) {
//        toast(employee.name.toString())
    }

    fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}