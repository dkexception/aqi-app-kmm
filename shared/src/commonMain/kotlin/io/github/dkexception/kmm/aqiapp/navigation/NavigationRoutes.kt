package io.github.dkexception.kmm.aqiapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class OnboardingRoutes {

    @Serializable
    data object OnboardingWelcome : OnboardingRoutes()

    @Serializable
    data object OnboardingGuide : OnboardingRoutes()
}

@Serializable
sealed class AuthRoutes {

    @Serializable
    data object AuthLogin : AuthRoutes()
}

@Serializable
sealed class HomeRoutes {

    @Serializable
    data object HomeMain : HomeRoutes()
}

@Serializable
sealed class DataBankRoutes {

    @Serializable
    data object DataBankMain : DataBankRoutes()
}

@Serializable
sealed class MoreRoutes {

    @Serializable
    data object MoreList : MoreRoutes()
}

@Serializable
sealed class AQIDetailsRoutes {

    @Serializable
    data object AQIDetails : AQIDetailsRoutes()

    @Serializable
    data object AQIScale : AQIDetailsRoutes()
}

@Serializable
sealed class OtherRoutes {

    @Serializable
    data object Invalid404 : OtherRoutes()
}
