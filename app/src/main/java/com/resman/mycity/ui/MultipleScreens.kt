package com.resman.mycity.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.resman.mycity.model.Category
import com.resman.mycity.model.Site
@Composable
fun CategorySitesAndDetail(
    selectedSite: Site,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    sites: List<Site>,
    categories: List<Category>,
    onClick: (Category) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onClickSite: (Site) -> Unit
) {
    Row {
        CategoriesList(
            categories = categories,
            onClick = onClick,
            modifier = modifier.weight(1f),
            contentPadding = contentPadding
        )
        SitesList(
            sites = sites,
            onClick = onClickSite,
            modifier = modifier.weight(1f),
            contentPadding = contentPadding,
            onBackPressed = onBackPressed
        )
        SitesDetail(
            selectedSite = selectedSite,
            onBackPressed = onBackPressed,
            modifier = modifier.weight(1f),
            contentPadding = contentPadding
        )
    }
}