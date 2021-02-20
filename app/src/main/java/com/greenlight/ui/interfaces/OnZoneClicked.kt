package com.greenlight.ui.interfaces

import com.greenlight.data.local.entities.Zone

interface OnZoneClicked {
    fun onItemClicked(zone: Zone)
}