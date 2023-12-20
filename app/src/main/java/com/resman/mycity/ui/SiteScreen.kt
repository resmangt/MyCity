package com.resman.mycity.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.resman.mycity.R
import com.resman.mycity.model.Site

@Composable
fun SitesList(
    sites: List<Site>,
    onClick: (Site) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onBackPressed: () -> Unit,
) {
    BackHandler {
        onBackPressed()
    }
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier,
    ) {
        items(sites) { site -> // Omitimos el 'key'
            SitesItem(
                site = site,
                onItemClick = onClick
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SitesItem(
    site: Site,
    onItemClick: (Site) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(site) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            ImageItem(
                imageResourceId = site.imageResourceId,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.card_image_height))
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
            ) {
                Text(
                    text = stringResource(site.title),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Text(
                    text = stringResource(site.description),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(Modifier.weight(1f))
                Row {
                    Spacer(Modifier.weight(1f))
                    Review(site = site)
                }
            }
        }
    }
}
@Composable
fun Review(modifier: Modifier = Modifier, site: Site){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text =  (site.reviews.toString()),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            overflow = TextOverflow.Ellipsis
        )
        Icon(
            painter = painterResource(R.drawable.star),
            contentDescription = "icon Star",
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
        )
    }
}