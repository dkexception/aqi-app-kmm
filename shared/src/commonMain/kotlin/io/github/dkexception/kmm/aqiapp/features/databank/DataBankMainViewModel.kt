package io.github.dkexception.kmm.aqiapp.features.databank

import androidx.lifecycle.viewModelScope
import io.github.dkexception.kmm.aqiapp.base.BaseViewModel
import io.github.dkexception.kmm.aqiapp.flow.KMMStateFlow
import io.github.dkexception.kmm.aqiapp.flow.common
import io.github.dkexception.kmm.aqiapp.utils.UIText
import io.github.dkexception.kmm.aqiapp.validators.ISingleStringValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DataBankMainViewModel(
    private val emailValidator: ISingleStringValidator
) : BaseViewModel<DataBankMainEvent>(), IDataBankMainViewModel {

    private val _state = MutableStateFlow(DataBankMainScreenState())
    override val state: KMMStateFlow<DataBankMainScreenState>
        get() = _state.map {

            val uiEmailError: UIText? = emailValidator(it.enteredEmailId, true)

            val shouldEnableMakeRequestButton: Boolean = uiEmailError == null

            it.copy(
                isMakeRequestButtonEnabled = shouldEnableMakeRequestButton,
                emailIdError = uiEmailError
            )

        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DataBankMainScreenState())
            .common()

    override fun onEvent(dataBankMainEvent: DataBankMainEvent) {
        when (dataBankMainEvent) {
            is DataBankMainEvent.OnCityChanged -> {
                _state.update {
                    it.copy(
                        enteredCity = dataBankMainEvent.newCity
                    )
                }
            }

            is DataBankMainEvent.OnDateChanged -> {
                _state.update {
                    it.copy(
                        selectedDate = dataBankMainEvent.newDate
                    )
                }
            }

            is DataBankMainEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        enteredEmailId = dataBankMainEvent.newEmail
                    )
                }
            }

            DataBankMainEvent.OnMakeRequestButtonClicked -> {
                // Show Snackbar something
            }
        }
    }
}
