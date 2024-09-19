package io.github.dkexception.kmm.aqiapp.features.more

data class MoreListScreenState(

    val userName: String = "",

    val emailId: String = "",

    val appName: String = "",

    val appVersion: String = "",

    val isConfirmLogoutPopupVisible: Boolean = false
)
