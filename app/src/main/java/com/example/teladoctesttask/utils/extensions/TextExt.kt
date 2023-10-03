package com.example.teladoctesttask.utils.extensions

fun Char.isWordPart() = isLetterOrDigit() || isApostrophe() || isHyphen()

fun Char.isWordEnd() = isSpace() || isPunctuationMark() || isDoubleQuote()

fun Char.isWordStart() = isSpace() || isDoubleQuote()

fun Char.isApostrophe() = code == 39

fun Char.isDoubleQuote() = code == 34

fun Char.isHyphen() = code == 45

fun Char.isSpace() = code == 32

// .,:;!?
fun Char.isPunctuationMark(): Boolean {
    return code == 33 || code == 44 || code == 63
            || code == 58 || code == 59 || code == 46
}

fun String.toQuery() = replace(" ", "").lowercase()