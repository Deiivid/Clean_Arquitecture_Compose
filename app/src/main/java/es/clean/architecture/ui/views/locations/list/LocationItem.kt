package es.clean.architecture.ui.views.locations.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import es.clean.architecture.R
import es.clean.architecture.domain.locations.models.RickyMortyLocationsModel
import es.clean.architecture.ui.common.Dimens.Custom150
import es.clean.architecture.ui.common.Dimens.Large


@Composable
fun LocationItem(
    location: RickyMortyLocationsModel.Location,
    onItemClick: (RickyMortyLocationsModel.Location) -> Unit,
) {
    val imageResId = when (location.name) {
        "Abadango" -> R.drawable.abadango
        "Earth" -> R.drawable.earth
        "Bird World" -> R.drawable.bird_world
        "Purge Planet" -> R.drawable.purge_planet
        "Citadel of Ricks" -> R.drawable.purge_planet
        "Alpha Centaurus" -> R.drawable.alpha
        "Worldender's lair" -> R.drawable.worldenders
        "Anatomy Park" -> R.drawable.anatomy
        "Interdimensional Cable" -> R.drawable.interdimensional
        "Immortality Field Resort" -> R.drawable.inmortality
        "Post Apocalyptic Earth" -> R.drawable.post_apocalyptic
        "Pizza Universe" -> R.drawable.pizza_universe
        "Prime Universe" -> R.drawable.prime_universe
        "Pluto" -> R.drawable.pluto
        "Scortia" -> R.drawable.scortia
        "Saturn" -> R.drawable.saturn
        "Shady Garage" -> R.drawable.shady
        "Smith Residence" -> R.drawable.smith
        "Solar System" -> R.drawable.solar
        "Gear World" -> R.drawable.gear

        else -> R.drawable.unknown
    }


    Box(
        modifier = Modifier
            .height(Custom150)
            .width(Custom150)
            .testTag("LocationItem") // Test
            .padding(Large)
            .clip(CircleShape)
            .background(Color.Transparent)
            .clickable { onItemClick(location) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .shadow(4.dp, CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = location.name,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
