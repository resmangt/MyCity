package com.resman.mycity.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.resman.mycity.R
import com.resman.mycity.model.Site

@Composable
fun SitesDetail(
    selectedSite: Site,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    )
    {
        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {
            Card(
                elevation = CardDefaults.cardElevation(),
                shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
            ) {
                Box {
                    Box {
                        Image(
                            painter = painterResource(selectedSite.imageResourceId),
                            contentDescription = null,
                            alignment = Alignment.TopCenter,
                            contentScale = ContentScale.FillWidth,
                        )
                    }
                    Column(
                        Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                    0f,
                                    400f
                                )
                            )
                    ) {
                        Text(
                            text = stringResource(selectedSite.title),
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(R.dimen.padding_small))
                        )
                        Row(
                            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                        ) {
                            Spacer(Modifier.weight(1f))
                            Review(site = selectedSite)
                        }
                    }
                }
                Text(
                    text = stringResource(selectedSite.description),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                        horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                    )
                )
            }
        }
    }
}