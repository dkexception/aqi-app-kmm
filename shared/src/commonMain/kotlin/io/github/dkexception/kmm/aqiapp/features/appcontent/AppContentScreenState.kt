package io.github.dkexception.kmm.aqiapp.features.appcontent

data class AppContentScreenState(

    val iOSShouldShowWelcomeScreen: Boolean = true,

    val postWelcomeDestination: PostWelcomeDestination = PostWelcomeDestination.UNKNOWN
)

enum class PostWelcomeDestination {

    UNKNOWN,

    GUIDE,

    LOGIN,

    HOME
}
