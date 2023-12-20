package com.resman.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val id: Int,
    @StringRes val title: Int,
    @DrawableRes val imageResourceId: Int,
    val sites : Int,
    val listSites:List<Site>
)
