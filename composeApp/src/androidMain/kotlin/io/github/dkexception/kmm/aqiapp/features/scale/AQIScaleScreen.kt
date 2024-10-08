@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.dkexception.kmm.aqiapp.features.scale

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import aqiappkmm.ui.generated.resources.Res
import aqiappkmm.ui.generated.resources.ic_chevron_left
import io.github.dkexception.kmm.aqiapp.R
import io.github.dkexception.ui.cards.DXCard
import io.github.dkexception.ui.dividers.DXDivider
import io.github.dkexception.ui.scaffold.DXScaffold
import io.github.dkexception.ui.theme.DXColors
import io.github.dkexception.ui.theme.DXPaddings
import io.github.dkexception.ui.theme.DXTheme
import io.github.dkexception.ui.theme.large
import io.github.dkexception.ui.theme.light
import io.github.dkexception.ui.theme.regular
import org.jetbrains.compose.resources.painterResource

@Composable
fun AQIScaleScreen(
    state: AQIScaleScreenState,
    onEvent: (AQIScaleEvent) -> Unit
) = AQIScaleScreenContent(state = state, onEvent = onEvent)

@Composable
private fun AQIScaleScreenContent(
    state: AQIScaleScreenState,
    onEvent: (AQIScaleEvent) -> Unit
) {

    DXScaffold(
        modifier = Modifier.fillMaxSize(),
        optionalContainerColor = DXColors.screenBackground.secondary,
        optionalTopBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "AQI Scale",
                        color = DXColors.text.dark
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(AQIScaleEvent.OnBackClicked)
                        }
                    ) {
                        Icon(
                            painter = painterResource(resource = Res.drawable.ic_chevron_left),
                            contentDescription = "Back",
                            tint = DXColors.text.dark
                        )
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = DXPaddings.default,
                    end = DXPaddings.default,
                    bottom = DXPaddings.default
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(DXPaddings.default))

            Text(
                text = "The Air Quality Index (AQI) is a standardized measure of air pollution levels, ranging from 0 to 500. It assesses various pollutants like particulate matter and ozone. The descriptions provided below offer guidance on actions to take at each level to protect health.",
                style = regular(),
                color = DXColors.text.light,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(DXPaddings.default))

            DXCard(Modifier.fillMaxSize()) {
                Column(Modifier.fillMaxSize()) {

                    AQIScaleInfoRow(
                        icon = R.drawable.ill_happy,
                        title = "Good",
                        rangeText = "0 - 50",
                        description = "Air quality is satisfactory, with little to no risk to health. Outdoor activities can be enjoyed without concerns regarding air pollution."
                    )
                    DXDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = DXPaddings.default)
                    )
                    AQIScaleInfoRow(
                        icon = R.drawable.ill_smile,
                        title = "Moderate",
                        rangeText = "51 - 100",
                        description = "Air quality may pose a slight health concern for sensitive individuals, but the general public is unlikely to be affected. Outdoor activities can still be pursued with some caution."
                    )
                    DXDivider(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = DXPaddings.default)
                    )
                    AQIScaleInfoRow(
                        icon = R.drawable.ill_neutral,
                        title = "Unhealthy for sensitive groups",
                        rangeText = "101 - 150",
                        description = "People with pre-existing health conditions, children, and the elderly may experience health effects. Outdoor activities should be limited for these groups, especially in areas with high pollution levels."
                    )
                    DXDivider(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = DXPaddings.default)
                    )
                    AQIScaleInfoRow(
                        icon = R.drawable.ill_sad,
                        title = "Unhealthy",
                        rangeText = "151 - 200",
                        description = "The general public may start to experience health effects, with prolonged exposure posing risks. Outdoor activities should be reduced, particularly for sensitive individuals, and precautions should be taken to minimize exposure to pollutants."
                    )
                    DXDivider(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = DXPaddings.default)
                    )
                    AQIScaleInfoRow(
                        icon = R.drawable.ill_sick,
                        title = "Very Unhealthy",
                        rangeText = "201 - 300",
                        description = "Health alerts are issued as everyone may experience more serious health effects. Outdoor activities are strongly discouraged, and individuals are advised to stay indoors and minimize exposure to outdoor air."
                    )
                    DXDivider(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = DXPaddings.default)
                    )
                    AQIScaleInfoRow(
                        icon = R.drawable.ill_dead,
                        title = "Hazardous",
                        rangeText = "301 - 500",
                        description = "Health warnings of emergency conditions are issued, with severe health effects expected for all individuals. Outdoor exposure should be avoided entirely, and staying indoors with air filtration systems is recommended for protection."
                    )
                }
            }

            Spacer(modifier = Modifier.height(DXPaddings.default))
        }
    }
}

@Composable
private fun AQIScaleInfoRow(
    @DrawableRes icon: Int,
    title: String,
    rangeText: String,
    description: String
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(DXPaddings.default),
    verticalAlignment = Alignment.Top
) {

    Image(
        modifier = Modifier.size(50.dp),
        painter = androidx.compose.ui.res.painterResource(id = icon),
        contentDescription = null
    )

    Spacer(modifier = Modifier.width(DXPaddings.default))

    Column(Modifier.fillMaxWidth()) {

        Text(
            text = title,
            style = large().copy(fontWeight = FontWeight.Bold),
            color = DXColors.primary.default,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(DXPaddings.small))

        Text(
            text = "AQI range: $rangeText",
            style = large(),
            color = DXColors.text.dark,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(DXPaddings.small))

        Text(
            text = description,
            style = light(),
            color = DXColors.text.light,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@PreviewLightDark
@Composable
private fun AQIScaleScreenPreview() = DXTheme {
    AQIScaleScreen(
        state = AQIScaleScreenState()
    ) { }
}
