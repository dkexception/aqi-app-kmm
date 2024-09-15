package io.github.dkexception.kmm.aqiapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.dkexception.kmm.aqiapp.features.auth.login.LoginScreen
import io.github.dkexception.kmm.aqiapp.features.auth.login.LoginScreenState
import io.github.dkexception.kmm.aqiapp.features.auth.login.LoginViewModel
import io.github.dkexception.kmm.aqiapp.features.invalid.Screen404
import io.github.dkexception.kmm.aqiapp.features.onboarding.guide.GuideScreen
import io.github.dkexception.kmm.aqiapp.features.onboarding.guide.GuideViewModel
import io.github.dkexception.kmm.aqiapp.features.onboarding.welcome.WelcomeScreen
import io.github.dkexception.kmm.aqiapp.features.onboarding.welcome.WelcomeViewModel
import io.github.dkexception.ui.navigation.DXNavTransitions
import org.koin.androidx.compose.koinViewModel

@Composable
fun AQIAppNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) = NavHost(
    modifier = modifier,
    navController = navHostController,
    startDestination = OnboardingRoutes.OnboardingWelcome,
    enterTransition = DXNavTransitions.enterTransition,
    exitTransition = DXNavTransitions.exitTransition,
    popEnterTransition = DXNavTransitions.popEnterTransition,
    popExitTransition = DXNavTransitions.popExitTransition
) {

    composable<OnboardingRoutes.OnboardingWelcome> {
        @Suppress("UNUSED_VARIABLE")
        val viewModel: WelcomeViewModel = koinViewModel()
        WelcomeScreen()
    }

    composable<OnboardingRoutes.OnboardingGuide> {

        val viewModel: GuideViewModel = koinViewModel()
        GuideScreen(viewModel::onEvent)
    }

    composable<AuthRoutes.AuthLogin> {
        val viewModel: LoginViewModel = koinViewModel()
        val state: LoginScreenState by viewModel.state.collectAsStateWithLifecycle()
        LoginScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }

    composable<OtherRoutes.Invalid404> {
        Screen404()
    }
}
