package com.example.teladoctesttask.data.features.poems.repository

import com.example.teladoctesttask.data.base.InternalException
import com.example.teladoctesttask.data.features.poems.datasource.local.PoemsLocalDataSource
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream

private const val FAKE_NAME = "Fake.txt"

@RunWith(MockitoJUnitRunner::class)
class PoemsRepositoryImplTest {

    @Mock
    private lateinit var localDataSource: PoemsLocalDataSource

    private lateinit var systemUnderTest: PoemsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        systemUnderTest = PoemsRepositoryImpl(localDataSource)
    }

    @Test
    fun `for a valid non-empty poem properly counts words occurrences`() {
        val inputStream = ByteArrayInputStream("A valid poem for a valid test".toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).containsEntry("a", 2)
        assertThat(result).containsEntry("valid", 2)
        assertThat(result).containsEntry("poem", 1)
        assertThat(result).containsEntry("for", 1)
        assertThat(result).containsEntry("test", 1)
        assertThat(result).hasSize(5)
    }

    @Test
    fun `for a valid poem with punctuation marks properly counts words occurrences`() {
        val inputStream = ByteArrayInputStream("A poem. A valid poem, but with punctuation marks!".toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).containsEntry("a", 2)
        assertThat(result).containsEntry("valid", 1)
        assertThat(result).containsEntry("poem", 2)
        assertThat(result).containsEntry("but", 1)
        assertThat(result).containsEntry("with", 1)
        assertThat(result).containsEntry("punctuation", 1)
        assertThat(result).containsEntry("marks", 1)
        assertThat(result).hasSize(7)
    }

    @Test
    fun `for a valid poem with direct speech properly counts words occurrences`() {
        val inputStream = ByteArrayInputStream("He said: \"It is done\". And he was right.".toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).containsEntry("he", 2)
        assertThat(result).containsEntry("said", 1)
        assertThat(result).containsEntry("it", 1)
        assertThat(result).containsEntry("is", 1)
        assertThat(result).containsEntry("and", 1)
        assertThat(result).containsEntry("was", 1)
        assertThat(result).containsEntry("right", 1)
        assertThat(result).hasSize(8)
    }

    @Test
    fun `for a valid poem with apostrophes properly counts words occurrences`() {
        val inputStream = ByteArrayInputStream("I'm the law. I'll tell you 'tis 'bout the law".toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).containsEntry("i'm", 1)
        assertThat(result).containsEntry("the", 2)
        assertThat(result).containsEntry("law", 2)
        assertThat(result).containsEntry("i'll", 1)
        assertThat(result).containsEntry("tell", 1)
        assertThat(result).containsEntry("you", 1)
        assertThat(result).containsEntry("'tis", 1)
        assertThat(result).containsEntry("'bout", 1)
        assertThat(result).hasSize(8)
    }

    @Test
    fun `for a poem with significant text mistakes not all words are counted`() {
        val inputStream = ByteArrayInputStream("I'''m... Okay,:\"but;It's. --just-a test".toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).doesNotContainKey("i'm")
        assertThat(result).containsEntry("okay", 1)
        assertThat(result).containsEntry("okay", 1)
        assertThat(result).containsEntry("but", 1)
        assertThat(result).doesNotContainKey("it's")
        assertThat(result).containsEntry("just-a", 1)
        assertThat(result).containsEntry("test", 1)
    }

    @Test
    fun `for a valid poem with cyrillic symbols properly counts words occurrences`() {
        val inputStream = ByteArrayInputStream("Тест такой тест.".toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).containsEntry("тест", 2)
        assertThat(result).containsEntry("такой", 1)
    }

    @Test
    fun `for a valid poem words occurrences are case insensitive`() {
        val inputStream = ByteArrayInputStream("Just a test, Test, tEst.".toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).containsEntry("test", 3)
        assertThat(result).containsEntry("just", 1)
        assertThat(result).containsEntry("a", 1)
        assertThat(result).hasSize(3)
    }

    @Test
    fun `for a valid poem words with hyphen are counted properly`() {
        val inputStream = ByteArrayInputStream("It's a muddle-headed non-trivial decision. Non-trivial decision.".toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).containsEntry("it's", 1)
        assertThat(result).containsEntry("a", 1)
        assertThat(result).containsEntry("muddle-headed", 1)
        assertThat(result).containsEntry("non-trivial", 2)
        assertThat(result).containsEntry("decision", 2)
        assertThat(result).hasSize(5)
    }

    @Test
    fun `for a valid poem all digits are counted properly`() {
        val inputStream = ByteArrayInputStream("50 Test 1st poem. 50 1900s years, 15-th".toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).containsEntry("test", 1)
        assertThat(result).containsEntry("poem", 1)
        assertThat(result).containsEntry("years", 1)
        assertThat(result).containsEntry("1st", 1)
        assertThat(result).containsEntry("1900s", 1)
        assertThat(result).containsEntry("15-th", 1)
        assertThat(result).containsEntry("50", 2)
        assertThat(result).hasSize(7)
    }

    @Test
    fun `for a valid long poem all words are counted properly`() {
        val sb = StringBuilder()
        for (i in 0 until 500) { sb.append("test, ") }
        for (i in 0 until 1000) { sb.append("try. ") }
        for (i in 0 until 5000) { sb.append("2nd; ") }

        val inputStream = ByteArrayInputStream(sb.toString().toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).containsEntry("test", 500)
        assertThat(result).containsEntry("try", 1000)
        assertThat(result).containsEntry("2nd", 5000)
        assertThat(result).hasSize(3)
    }

    @Test(expected = InternalException::class)
    fun `for a wrong name poem exception is thrown`() {
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(null)
        systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)
    }

    @Test
    fun `for an empty poem the map of occurrences is empty`() {
        val inputStream = ByteArrayInputStream("".toByteArray())
        `when`(localDataSource.getPoem(FAKE_NAME)).thenReturn(inputStream)
        val result = systemUnderTest.getPoemWordsOccurrences(FAKE_NAME)

        assertThat(result).isEmpty()
    }
}