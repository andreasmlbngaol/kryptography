package com.andreasmlbngaol.kryptography.features.caesar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreasmlbngaol.kryptography.core.data.isAlphabetWithSpace
import com.andreasmlbngaol.kryptography.core.domain.EncodingTable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CaesarViewModel: ViewModel() {
    private val _state = MutableStateFlow(CaesarState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.collect { current ->
                val shouldEnable = current.plainText.isNotEmpty() && current.plainKey in 0..25
                if (current.encryptedButtonEnabled != shouldEnable) {
                    _state.value = current.copy(encryptedButtonEnabled = shouldEnable)
                }
            }
        }
    }

    fun selectMenu(index: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                selectedMenuIndex = index
            )
        }
    }

    fun changePlainText(text: String) {
        viewModelScope.launch {
            if(text.isEmpty() || text.isAlphabetWithSpace()) {
                _state.value = _state.value.copy(
                    plainText = text.uppercase()
                )
            }
        }
    }

    fun decrementPlainKey() {
        viewModelScope.launch {
            if(_state.value.plainKey > 0) {
                _state.value = _state.value.copy(
                    plainKey = _state.value.plainKey - 1
                )
            }
        }
    }

    fun incrementPlainKey() {
        viewModelScope.launch {
            if(_state.value.plainKey < 25) {
                _state.value = _state.value.copy(
                    plainKey = _state.value.plainKey + 1
                )
            }
        }
    }

    fun encryptText() {
        viewModelScope.launch {
            val plainText = _state.value.plainText
            val plainKey = _state.value.plainKey
            val encodingTable = EncodingTable()

            val encryptedText = plainText.split(" ")
                .joinToString(" ") { word ->
                    word.map { char ->
                        encodingTable.data[(char - 'A' + plainKey) % encodingTable.length]
                    }.joinToString("")
                }

            _state.value = _state.value.copy(
                encryptedText = encryptedText
            )
        }
    }

}