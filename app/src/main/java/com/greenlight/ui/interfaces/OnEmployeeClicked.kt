package com.greenlight.ui.interfaces

import com.greenlight.data.local.entities.Employee

interface OnEmployeeClicked {
    fun onItemClicked(employee: Employee)
}