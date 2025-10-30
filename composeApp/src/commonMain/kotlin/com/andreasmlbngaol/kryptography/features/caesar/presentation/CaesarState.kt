package com.andreasmlbngaol.kryptography.features.caesar.presentation

data class CaesarState(
    val selectedMenuIndex: Int = 0,

    val plainText: String = "",
    val plainKey: Int = 0,
    val encryptedText: String = "",
    val encryptedButtonEnabled: Boolean = false,

    val cipherText: String = "",
    val cipherKey: Int = 0,
    val decryptedText: String = ""
)
