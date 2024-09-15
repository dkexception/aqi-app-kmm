package io.github.dkexception.kmm.aqiapp.features.onboarding.guide

sealed class GuideEvent {

    data object OnSkipClicked : GuideEvent()

    data object OnGetStartedClicked : GuideEvent()
}
