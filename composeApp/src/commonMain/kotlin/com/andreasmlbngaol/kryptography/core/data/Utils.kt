package com.andreasmlbngaol.kryptography.core.data

fun String.isAlphabetWithSpace(): Boolean = this.all { char -> char.isLetter() || char.isWhitespace()}