package io.github.dkexception.kmm.aqiapp.features.databank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import aqiappkmm.ui.generated.resources.Res
import aqiappkmm.ui.generated.resources.ic_calendar
import aqiappkmm.ui.generated.resources.ic_location
import aqiappkmm.ui.generated.resources.ic_mail
import io.github.dkexception.kmm.aqiapp.R
import io.github.dkexception.kmm.aqiapp.extensions.asString
import io.github.dkexception.ui.buttons.DXPrimaryButton
import io.github.dkexception.ui.images.DXIllustration
import io.github.dkexception.ui.inputs.DXTextField
import io.github.dkexception.ui.theme.DXColors
import io.github.dkexception.ui.theme.DXPaddings
import io.github.dkexception.ui.theme.DXTheme
import io.github.dkexception.ui.theme.headline1
import io.github.dkexception.ui.theme.regular

@Composable
fun DataBankMainScreen(
    state: DataBankMainScreenState,
    onEvent: (DataBankMainEvent) -> Unit
) = DataBankMainScreenContent(state = state, onEvent = onEvent)

@Composable
private fun DataBankMainScreenContent(
    state: DataBankMainScreenState,
    onEvent: (DataBankMainEvent) -> Unit
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(DXColors.screenBackground.secondary)
        .padding(horizontal = DXPaddings.large)
        .statusBarsPadding()
        .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
) {

    DXIllustration(
        illustration = R.drawable.ill_databank,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f),
        optionalContentScale = ContentScale.Fit
    )

    Spacer(modifier = Modifier.height(DXPaddings.large))

    Text(
        text = "Request for data",
        style = headline1(),
        color = DXColors.text.dark,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(DXPaddings.small))

    Text(
        text = "If you would like to download pollutant data, please place your request here. We’ll share the AQI data along with a link to the Live Emission Visualiser - Data Bank dashboard.",
        style = regular(),
        color = DXColors.text.light,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(DXPaddings.large))

    DXTextField(
        text = state.enteredEmailId,
        modifier = Modifier.fillMaxWidth(),
        isSingleLine = true,
        optionalSupportingText = state.emailIdError?.asString(LocalContext.current),
        optionalPlaceholderText = "Your email address",
        optionalLeadingIcon = Res.drawable.ic_mail,
        optionalKeyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    ) {
        onEvent(DataBankMainEvent.OnEmailChanged(it))
    }

    Spacer(modifier = Modifier.height(DXPaddings.small))

    DXTextField(
        text = state.enteredCity,
        modifier = Modifier.fillMaxWidth(),
        isSingleLine = true,
        optionalPlaceholderText = "Your city",
        optionalLeadingIcon = Res.drawable.ic_location,
        optionalKeyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    ) {
        onEvent(DataBankMainEvent.OnCityChanged(it))
    }

    Spacer(modifier = Modifier.height(DXPaddings.small))

    DXTextField(
        text = state.selectedDate,
        modifier = Modifier.fillMaxWidth(),
        isSingleLine = true,
        optionalPlaceholderText = "Select Dates",
        optionalLeadingIcon = Res.drawable.ic_calendar,
        optionalKeyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Go
        ),
        optionalKeyboardActions = KeyboardActions(
            onGo = {
                if (state.isMakeRequestButtonEnabled) {
                    onEvent(DataBankMainEvent.OnMakeRequestButtonClicked)
                }
            }
        )
    ) {
        onEvent(DataBankMainEvent.OnDateChanged(it))
    }

    Spacer(modifier = Modifier.height(DXPaddings.large))

    Spacer(modifier = Modifier.weight(1f))

    DXPrimaryButton(
        text = "Make Request",
        isEnable = state.isMakeRequestButtonEnabled,
        modifier = Modifier.fillMaxWidth()
    ) {
        onEvent(DataBankMainEvent.OnMakeRequestButtonClicked)
    }
}

@Preview
@Composable
private fun DataBankMainScreenPreview() = DXTheme {
    DataBankMainScreen(
        state = DataBankMainScreenState()
    ) { }
}
