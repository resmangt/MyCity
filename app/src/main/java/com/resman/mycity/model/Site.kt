package com.resman.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.resman.mycity.R

data class Site(
    @DrawableRes val imageResourceId: Int,
    @StringRes val title: Int,
    @StringRes val description: Int = R.string.site_detail_text,
    val reviews: Double
)