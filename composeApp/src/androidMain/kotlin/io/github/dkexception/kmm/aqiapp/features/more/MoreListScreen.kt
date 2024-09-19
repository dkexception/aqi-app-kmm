package io.github.dkexception.kmm.aqiapp.features.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aqiappkmm.ui.generated.resources.Res
import aqiappkmm.ui.generated.resources.ic_about_us
import aqiappkmm.ui.generated.resources.ic_chevron_right
import aqiappkmm.ui.generated.resources.ic_contact_us
import aqiappkmm.ui.generated.resources.ic_faq
import aqiappkmm.ui.generated.resources.ic_location
import aqiappkmm.ui.generated.resources.ic_logout
import aqiappkmm.ui.generated.resources.ic_profile
import aqiappkmm.ui.generated.resources.ic_settings
import io.github.dkexception.kmm.aqiapp.R
import io.github.dkexception.kmm.aqiapp.extensions.asString
import io.github.dkexception.kmm.aqiapp.utils.UIText
import io.github.dkexception.ui.cards.DXCard
import io.github.dkexception.ui.dialogs.DXAlertDialog
import io.github.dkexception.ui.dividers.DXDivider
import io.github.dkexception.ui.images.DXIllustration
import io.github.dkexception.ui.theme.DXColors
import io.github.dkexception.ui.theme.DXPaddings
import io.github.dkexception.ui.theme.DXTheme
import io.github.dkexception.ui.theme.headline2
import io.github.dkexception.ui.theme.regular
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MoreListScreen(
    state: MoreListScreenState,
    onEvent: (MoreListEvent) -> Unit
) = MoreListScreenContent(state = state, onEvent = onEvent)

@Composable
private fun MoreListScreenContent(
    state: MoreListScreenState,
    onEvent: (MoreListEvent) -> Unit
) {

    if (state.isConfirmLogoutPopupVisible) {
        DXAlertDialog(
            title = "Log out?",
            optionalSubtitle = "Logging out from the app will clear all your saved locations and data.",
            primaryButtonTextToActionPair = "Confirm" to {
                onEvent(MoreListEvent.OnConfirmLogoutPopupAction(true))
            },
            optionalSecondaryButtonTextToActionPair = "Cancel" to {
                onEvent(MoreListEvent.OnConfirmLogoutPopupAction(false))
            }
        ) { }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DXColors.screenBackground.secondary),
        contentAlignment = Alignment.Center
    ) {

        DXIllustration(
            illustration = R.drawable.ill_more_bg,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            optionalContentScale = ContentScale.FillWidth
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding(),
            contentPadding = PaddingValues(
                top = LocalConfiguration.current.screenHeightDp.dp * .13f,
                end = DXPaddings.large,
                bottom = DXPaddings.large,
                start = DXPaddings.large
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(DXColors.accent.light),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = androidx.compose.ui.res.painterResource(
                            id = R.drawable.ic_launcher_foreground
                        ),
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Text(
                    text = state.userName,
                    color = DXColors.text.dark,
                    style = headline2()
                )

                Spacer(modifier = Modifier.height(DXPaddings.small))

                Text(
                    text = state.emailId,
                    color = DXColors.text.light,
                    style = regular()
                )

                Spacer(modifier = Modifier.height(DXPaddings.default))

                DXDivider(Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(DXPaddings.default))
            }

            items(
                listOf(
                    Triple(
                        Res.drawable.ic_profile,
                        UIText.DynamicString("Profile"),
                        MoreListItem.PROFILE
                    ),
                    Triple(
                        Res.drawable.ic_location,
                        UIText.DynamicString("Saved Locations"),
                        MoreListItem.SAVED_LOCATIONS
                    ),
                    Triple(
                        Res.drawable.ic_faq,
                        UIText.DynamicString("FAQ"),
                        MoreListItem.FAQ
                    )
                )
            ) {

                MoreItemRow(
                    icon = it.first,
                    name = it.second
                ) {
                    onEvent(MoreListEvent.OnItemClicked(it.third))
                }

                Spacer(modifier = Modifier.height(DXPaddings.small))
            }

            item {
                Spacer(modifier = Modifier.height(DXPaddings.default))

                DXDivider(Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(DXPaddings.default))
            }

            items(
                listOf(
                    Triple(
                        Res.drawable.ic_settings,
                        UIText.DynamicString("Settings"),
                        MoreListItem.SETTINGS
                    ),
                    Triple(
                        Res.drawable.ic_about_us,
                        UIText.DynamicString("About Us"),
                        MoreListItem.ABOUT_US
                    ),
                    Triple(
                        Res.drawable.ic_contact_us,
                        UIText.DynamicString("Contact Us"),
                        MoreListItem.CONTACT_US
                    ),
                    Triple(
                        Res.drawable.ic_logout,
                        UIText.DynamicString("Logout"),
                        MoreListItem.LOGOUT
                    )
                )
            ) {

                MoreItemRow(
                    icon = it.first,
                    name = it.second
                ) {
                    onEvent(MoreListEvent.OnItemClicked(it.third))
                }

                Spacer(modifier = Modifier.height(DXPaddings.small))
            }

            item {
                Spacer(modifier = Modifier.height(DXPaddings.default))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = state.appName,
                        style = headline2(),
                        color = DXColors.text.dark
                    )

                    Spacer(modifier = Modifier.height(DXPaddings.small))

                    Text(
                        text = "App Version ${state.appVersion}",
                        style = regular(),
                        color = DXColors.text.light
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(DXPaddings.default))
    }
}

@Composable
private fun MoreItemRow(
    icon: DrawableResource,
    name: UIText,
    onClickAction: () -> Unit
) = DXCard(
    modifier = Modifier.fillMaxWidth(),
    onClickAction = onClickAction
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = DXPaddings.default,
                vertical = DXPaddings.default
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = DXColors.primary.dark
        )

        Spacer(modifier = Modifier.width(DXPaddings.default))

        Text(
            text = name.asString(),
            style = regular().copy(
                lineHeight = 24.sp
            ),
            color = DXColors.text.dark
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(Res.drawable.ic_chevron_right),
            contentDescription = null,
            tint = DXColors.primary.dark
        )
    }
}

@PreviewLightDark
@Composable
private fun MoreListScreenPreview() = DXTheme {
    MoreListScreen(
        state = MoreListScreenState(
            userName = "Dhanesh Katre",
            emailId = "dkexception@gmail.com",
            appName = "AQI App",
            appVersion = "1.0"
        )
    ) { }
}

@PreviewLightDark
@Composable
private fun MoreListScreenLogoutPreview() = DXTheme {
    MoreListScreen(
        state = MoreListScreenState(
            userName = "Dhanesh Katre",
            emailId = "dkexception@gmail.com",
            appName = "AQI App",
            appVersion = "1.0",
            isConfirmLogoutPopupVisible = true
        )
    ) { }
}
