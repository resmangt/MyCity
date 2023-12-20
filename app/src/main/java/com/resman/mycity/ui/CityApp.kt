package com.resman.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.resman.mycity.R
import com.resman.mycity.util.CityContentType
import com.resman.mycity.util.ScreenState
import com.resman.mycity.util.paddingCard

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
) {
    val viewModel = viewModel<CityViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val contentType = getContentType(windowSize)

    Scaffold(
        topBar = { CityAppBar(windowSize = windowSize, screenState= uiState.screenState, onBackButtonClick = viewModel::onBackPressed) }
    ) { innerPadding ->
        ContentArea(contentType, uiState, viewModel, innerPadding, onBackPressed)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppBar(
    onBackButtonClick: () -> Unit,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    screenState: ScreenState
) {
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            TopAppBar(
                title = {
                    Text(
                        text = getTitleForAppBar(screenState),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = if (screenState != ScreenState.CATEGORY) {
                    {
                        IconButton(onClick = onBackButtonClick) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "volver"
                            )
                        }
                    }

                } else {
                    { Box {} }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = modifier
            )
        }

        WindowWidthSizeClass.Medium -> {
            Row(modifier = Modifier.fillMaxWidth()) {
                TopAppBar(
                    modifier = Modifier.weight(1f),
                    title = {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = stringResource(R.string.list_fragment_category),
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
                TopAppBar(
                    modifier = Modifier.weight(1f),
                    title = {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text =
                                if (screenState == ScreenState.SITES || screenState == ScreenState.CATEGORY) {
                                    stringResource(id = R.string.list_sites)
                                } else {
                                    stringResource(id = R.string.site_details)
                                },
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                    navigationIcon = if (screenState == ScreenState.DETAILSITE) {
                        {
                            IconButton(onClick = onBackButtonClick) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "volver"
                                )
                            }
                        }

                    } else {
                        { Box {} }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            }

        }

        WindowWidthSizeClass.Expanded -> {
            TopAppBar(
                title = {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = stringResource(R.string.list_fragment_category))
                        Text(text = stringResource(id = R.string.list_sites))
                        Text(text = stringResource(id = R.string.site_details))
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = modifier,
            )
        }
    }
}
@Composable
fun getTitleForAppBar(screenState: ScreenState): String {
    return when (screenState) {
        ScreenState.CATEGORY -> stringResource(R.string.list_fragment_category)
        ScreenState.SITES -> stringResource(R.string.list_sites)
        ScreenState.DETAILSITE -> stringResource(R.string.site_details)
    }
}
@Composable
fun ContentArea(
    contentType: CityContentType,
    uiState: CityUiState,
    viewModel: CityViewModel,
    innerPadding: PaddingValues,
    onBackPressed: () -> Unit
) {
    when (contentType) {
        CityContentType.OneList -> OneListContent(uiState, viewModel, innerPadding)
        CityContentType.TwoList -> TwoListContent(uiState, viewModel, innerPadding)
        CityContentType.ThreeList -> ThreeListContent(uiState, viewModel, innerPadding, onBackPressed)
    }
}
@Composable
fun OneListContent(
    uiState: CityUiState,
    viewModel: CityViewModel,
    innerPadding: PaddingValues
) {
    when (uiState.screenState) {
        ScreenState.CATEGORY -> {
            CategoriesList(
                categories = uiState.categoryList,
                onClick = { category ->
                    viewModel.updateCurrentCategory(category)
                    viewModel.navigateToSitesPage()
                },
                contentPadding = innerPadding,
                modifier = Modifier
                    .paddingCard()
            )
        }

        ScreenState.SITES -> {
            SitesList(
                sites = uiState.currentCategory.listSites,
                onClick = { site ->
                    viewModel.navigateToDetailSitePage(site)
                },
                modifier = Modifier.paddingCard(),
                contentPadding = innerPadding,
                onBackPressed = { viewModel.navigateToCategoryPage() }
            )
        }

        ScreenState.DETAILSITE -> {
            SitesDetail(
                selectedSite = uiState.currentSite,
                onBackPressed = { viewModel.navigateToSitesPage() },
                modifier = Modifier.paddingCard(),
                contentPadding = innerPadding
            )
        }
    }
}
@Composable
fun TwoListContent(
    uiState: CityUiState,
    viewModel: CityViewModel,
    innerPadding: PaddingValues
) {
    Row {
        CategoriesList(
            categories = uiState.categoryList,
            onClick = { category ->
                viewModel.updateCurrentCategory(category)
                viewModel.navigateToSitesPage()
            },
            contentPadding = innerPadding,
            modifier = Modifier
                .weight(1f)
                .padding(top = 10.dp, start = 16.dp)
        )
        when (uiState.screenState) {
            ScreenState.CATEGORY, ScreenState.SITES -> {
                SitesList(
                    sites = uiState.currentCategory.listSites,
                    onClick = { site ->
                        viewModel.navigateToDetailSitePage(site)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .paddingCard(),
                    contentPadding = innerPadding,
                    onBackPressed = { viewModel.navigateToCategoryPage() }
                )
            }

            ScreenState.DETAILSITE -> {
                SitesDetail(
                    selectedSite = uiState.currentSite,
                    onBackPressed = { viewModel.navigateToSitesPage() },
                    modifier = Modifier
                        .weight(1f)
                        .paddingCard(),
                    contentPadding = innerPadding
                )
            }
        }
    }
}
@Composable
fun ThreeListContent(
    uiState: CityUiState,
    viewModel: CityViewModel,
    innerPadding: PaddingValues,
    onBackPressed: () -> Unit
) {
    CategorySitesAndDetail(
        selectedSite = uiState.currentSite,
        onBackPressed = { onBackPressed() },
        modifier = Modifier.paddingCard(),
        sites = uiState.currentCategory.listSites,
        categories = uiState.categoryList,
        onClick = { category ->
            viewModel.updateCurrentCategory(category)
            viewModel.navigateToSitesPage()
        },
        contentPadding = innerPadding,
        onClickSite = { site ->
            viewModel.selectSite(site)
        })
}

fun getContentType(windowSize: WindowWidthSizeClass): CityContentType {
    return when (windowSize) {
        WindowWidthSizeClass.Compact -> CityContentType.OneList
        WindowWidthSizeClass.Medium -> CityContentType.TwoList
        WindowWidthSizeClass.Expanded -> CityContentType.ThreeList
        else -> CityContentType.OneList
    }
}
@Composable
fun ImageItem(imageResourceId: Int, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(imageResourceId),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }
}