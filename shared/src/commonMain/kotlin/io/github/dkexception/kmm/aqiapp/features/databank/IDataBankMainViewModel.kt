package io.github.dkexception.kmm.aqiapp.features.databank

import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow

interface IDataBankMainViewModel {

    val state: KMMStateFlow<DataBankMainScreenState>

    fun onEvent(dataBankMainEvent: DataBankMainEvent)
}
