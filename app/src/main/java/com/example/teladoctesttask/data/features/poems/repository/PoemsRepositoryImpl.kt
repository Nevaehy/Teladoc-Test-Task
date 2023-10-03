package com.example.teladoctesttask.data.features.poems.repository

import com.example.teladoctesttask.data.base.InternalException
import com.example.teladoctesttask.data.features.poems.datasource.local.PoemsLocalDataSource
import com.example.teladoctesttask.utils.extensions.inc
import com.example.teladoctesttask.utils.extensions.isApostrophe
import com.example.teladoctesttask.utils.extensions.isSpace
import com.example.teladoctesttask.utils.extensions.isWordEnd
import com.example.teladoctesttask.utils.extensions.isWordPart
import com.example.teladoctesttask.utils.extensions.isWordStart
import javax.inject.Inject

class PoemsRepositoryImpl @Inject constructor(
    private val localDataSource: PoemsLocalDataSource
) : PoemsRepository {

    override fun getPoemWordsOccurrences(poemName: String): Map<String, Int> {
        val wordsMap = mutableMapOf<String, Int>()

        localDataSource.getPoem(poemName)?.let { inputStream ->
            inputStream.reader().forEachLine {
                processLine(it, wordsMap)
            }
        } ?: throw (InternalException())

        return wordsMap
    }

    /**
     * find all words and store their occurrences in the map
     * 1. we consider apostrophe and hyphen as a part of the word
     * only this (') one, but not (`) or others
    */
    private fun processLine(line: String, wordsMap: MutableMap<String, Int>) {
        val sb = StringBuilder()
        var prevChar = ' '

        line.forEach { ch ->
            when {
                ch.isWordEnd() && sb.isNotEmpty() -> {
                    wordsMap.inc(sb.toString())
                    sb.clear()
                }
                isSameWord(prevChar, ch) || isNewWord(prevChar, ch) -> sb.append(ch.lowercaseChar())
                else -> sb.clear()
            }
            prevChar = ch
        }
        // add the last word of the line if there is such
        if (sb.isNotEmpty()) wordsMap.inc(sb.toString())
    }

    private fun isSameWord(prevChar: Char, curChar: Char): Boolean {
        return (curChar.isWordPart() && prevChar.isLetterOrDigit())
                || curChar.isLetterOrDigit() && prevChar.isWordPart()
    }

    private fun isNewWord(prevChar: Char, curChar: Char): Boolean {
        return (prevChar.isWordStart() && curChar.isLetterOrDigit())
                || (prevChar.isSpace() && curChar.isApostrophe())
    }
}