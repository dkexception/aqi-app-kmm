package io.github.dkexception.kmm.aqiapp.features.more

sealed class MoreListEvent {

    data class OnItemClicked(val item: MoreListItem) : MoreListEvent()

    data class OnConfirmLogoutPopupAction(val isConfirm: Boolean) : MoreListEvent()
}

enum class MoreListItem {

    PROFILE,

    SAVED_LOCATIONS,

    FAQ,

    SETTINGS,

    ABOUT_US,

    CONTACT_US,

    LOGOUT
}
