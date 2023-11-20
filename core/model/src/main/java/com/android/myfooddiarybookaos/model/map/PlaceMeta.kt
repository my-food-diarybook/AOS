package com.android.myfooddiarybookaos.model.map

data class PlaceMeta(
    var total_count: Int,
    var pageable_count: Int,
    var is_end: Boolean,
    var same_name: RegionInfo
)