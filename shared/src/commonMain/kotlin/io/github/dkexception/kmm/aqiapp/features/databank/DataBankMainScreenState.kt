package io.github.dkexception.kmm.aqiapp.features.databank

import io.github.dkexception.kmm.aqiapp.utils.UIText

data class DataBankMainScreenState(

    val enteredEmailId: String = "",
    val emailIdError: UIText? = null,

    val enteredCity: String = "",

    val selectedDate: String = "",

    val isMakeRequestButtonEnabled: Boolean = false
)
