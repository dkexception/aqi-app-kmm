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
import io.github.dkexception.kmm.aqiapp.features.more.MoreListScreen
import io.github.dkexception.kmm.aqiapp.features.more.MoreListScreenState
import io.github.dkexception.kmm.aqiapp.features.more.MoreListViewModel
import io.github.dkexception.kmm.aqiapp.features.onboarding.guide.GuideScreen
import io.github.dkexception.kmm.aqiapp.features.onboarding.guide.GuideViewModel
import io.github.dkexception.kmm.aqiapp.features.onboarding.welcome.WelcomeScreen
import io.github.dkexception.kmm.aqiapp.features.onboarding.welcome.WelcomeViewModel
import io.github.dkexception.kmm.aqiapp.features.scale.AQIScaleScreen
import io.github.dkexception.kmm.aqiapp.features.scale.AQIScaleScreenState
import io.github.dkexception.kmm.aqiapp.features.scale.AQIScaleViewModel
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

    composable<AQIDetailsRoutes.AQIScale> {
        val viewModel: AQIScaleViewModel = koinViewModel()
        val state: AQIScaleScreenState by viewModel.state.collectAsStateWithLifecycle()
        AQIScaleScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }
    composable<AQIDetailsRoutes.AQIDetails> { }

    composable<MoreRoutes.MoreList> {
        val viewModel: MoreListViewModel = koinViewModel()
        val state: MoreListScreenState by viewModel.state.collectAsStateWithLifecycle()
        MoreListScreen(
            state = state,
            onEvent = viewModel::onEvent
        )
    }

    composable<OtherRoutes.Invalid404> {
        Screen404()
    }
}
