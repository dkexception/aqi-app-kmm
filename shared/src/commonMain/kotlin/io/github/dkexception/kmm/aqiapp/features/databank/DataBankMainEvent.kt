package io.github.dkexception.kmm.aqiapp.features.databank

sealed class DataBankMainEvent {

    data class OnEmailChanged(val newEmail: String) : DataBankMainEvent()

    data class OnCityChanged(val newCity: String) : DataBankMainEvent()

    data class OnDateChanged(val newDate: String) : DataBankMainEvent()

    data object OnMakeRequestButtonClicked : DataBankMainEvent()
}
