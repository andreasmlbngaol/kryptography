package com.andreasmlbngaol.kryptography.core.domain

class EncodingTable(
    val length: Int = 26
) {
    val data = ('A'..('A'.code + length - 1).toChar()).toList()
}