package com.example.teladoctesttask.domain.features.poems

import com.example.teladoctesttask.data.features.poems.repository.PoemsRepository
import com.example.teladoctesttask.domain.base.Outcome
import com.example.teladoctesttask.domain.features.poems.mappers.PoemMapToPoemDataMapper
import com.example.teladoctesttask.domain.features.poems.models.PoemData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

private const val FAKE_NAME = "Fake.txt"

@RunWith(MockitoJUnitRunner::class)
class PoemsInteractorTest {

    @Mock
    private lateinit var poemsRepository: PoemsRepository

    private lateinit var systemUnderTest: PoemsInteractorImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        systemUnderTest = PoemsInteractorImpl(poemsRepository, PoemMapToPoemDataMapper())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `for a valid poem name success is returned`() = runTest {
        val wordsOccurrences = mapOf(
            "test" to 2,
            "try" to 3,
            "okay" to 1
        )
        `when`(poemsRepository.getPoemWordsOccurrences(FAKE_NAME)).thenReturn(wordsOccurrences)
        val result = systemUnderTest.getPoemData(FAKE_NAME)

        assertThat(result).isInstanceOf(Outcome.Success::class.java)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `for a valid poem name sorted data by frequency is returned`() = runTest {
        val wordsOccurrences = mapOf(
            "test" to 2,
            "try" to 3,
            "okay" to 1
        )
        `when`(poemsRepository.getPoemWordsOccurrences(FAKE_NAME)).thenReturn(wordsOccurrences)
        val result = systemUnderTest.getPoemData(FAKE_NAME)
        val resultData: List<PoemData> = (result as Outcome.Success).data

        assertThat(resultData).hasSize(3)
        assertThat(resultData.first()).isEqualTo(PoemData("try", 3))
        assertThat(resultData[1]).isEqualTo(PoemData("test", 2))
        assertThat(resultData.last()).isEqualTo(PoemData("okay", 1))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `for a wrong poem name error is returned`() = runTest {
        `when`(poemsRepository.getPoemWordsOccurrences(FAKE_NAME)).thenThrow(RuntimeException())
        val result = systemUnderTest.getPoemData(FAKE_NAME)

        assertThat(result).isInstanceOf(Outcome.Error::class.java)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `sort by frequency returns correct data`() = runTest {
        val wordsOccurrences = mapOf(
            "test" to 2,
            "try" to 3,
            "okay" to 1
        )
        `when`(poemsRepository.getPoemWordsOccurrences(FAKE_NAME)).thenReturn(wordsOccurrences)
        systemUnderTest.getPoemData(FAKE_NAME)
        val result = systemUnderTest.getSortedPoemData(PoemsInteractorImpl.Sort.FREQUENCY)

        assertThat(result).hasSize(3)
        assertThat(result.first()).isEqualTo(PoemData("try", 3))
        assertThat(result[1]).isEqualTo(PoemData("test", 2))
        assertThat(result.last()).isEqualTo(PoemData("okay", 1))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `sort alphabetically returns correct data`() = runTest {
        val wordsOccurrences = mapOf(
            "miracle" to 2,
            "abba" to 3,
            "cloud" to 1
        )
        `when`(poemsRepository.getPoemWordsOccurrences(FAKE_NAME)).thenReturn(wordsOccurrences)
        systemUnderTest.getPoemData(FAKE_NAME)
        val result = systemUnderTest.getSortedPoemData(PoemsInteractorImpl.Sort.ALPHABET)

        assertThat(result).hasSize(3)
        assertThat(result.first()).isEqualTo(PoemData("abba", 3))
        assertThat(result[1]).isEqualTo(PoemData("cloud", 1))
        assertThat(result.last()).isEqualTo(PoemData("miracle", 2))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `sort by word length returns correct data`() = runTest {
        val wordsOccurrences = mapOf(
            "miracle" to 2,
            "abba" to 3,
            "cloud" to 1
        )
        `when`(poemsRepository.getPoemWordsOccurrences(FAKE_NAME)).thenReturn(wordsOccurrences)
        systemUnderTest.getPoemData(FAKE_NAME)
        val result = systemUnderTest.getSortedPoemData(PoemsInteractorImpl.Sort.WORD_LENGTH)

        assertThat(result).hasSize(3)
        assertThat(result.first()).isEqualTo(PoemData("abba", 3))
        assertThat(result[1]).isEqualTo(PoemData("cloud", 1))
        assertThat(result.last()).isEqualTo(PoemData("miracle", 2))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `sort returns empty data if cache is cleared`() = runTest {
        val wordsOccurrences = mapOf(
            "miracle" to 2,
            "abba" to 3,
            "cloud" to 1
        )
        `when`(poemsRepository.getPoemWordsOccurrences(FAKE_NAME)).thenReturn(wordsOccurrences)
        systemUnderTest.getPoemData(FAKE_NAME)
        systemUnderTest.clearAllCache()
        val result = systemUnderTest.getSortedPoemData(PoemsInteractorImpl.Sort.ALPHABET)

        assertThat(result).hasSize(0)
    }

}