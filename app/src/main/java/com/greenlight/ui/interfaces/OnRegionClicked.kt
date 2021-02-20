package com.greenlight.ui.interfaces

import com.greenlight.data.local.entities.Region

interface OnRegionClicked {
    fun onItemClicked(region: Region)
}