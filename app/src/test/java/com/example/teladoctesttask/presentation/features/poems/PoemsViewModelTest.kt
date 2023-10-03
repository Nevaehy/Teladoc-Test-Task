package com.example.teladoctesttask.presentation.features.poems

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.teladoctesttask.domain.features.poems.PoemsInteractor
import com.example.teladoctesttask.helper.MainDispatcherRule
import com.example.teladoctesttask.presentation.features.poems.mappers.PoemDataToWordsOccurrencesMapper
import com.example.teladoctesttask.presentation.features.poems.mappers.SortMapper
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

private const val FAKE_NAME = "Fake.txt"

//TODO: hadn't enough time to setup entire testing infrastructure :(
//@RunWith(MockitoJUnitRunner::class)
//@HiltAndroidTest
//class PoemsViewModelTest {
//
//    @get:Rule
//    var instantTaskExecutionRule = InstantTaskExecutorRule()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @get:Rule
//    val mainDispatcherRule = MainDispatcherRule()
//
//    @BindValue
//    @JvmField
//    val poemsInteractor: PoemsInteractor = Mockito.mock(PoemsInteractor::class.java)
//
//    private lateinit var systemUnderTest: PoemsViewModel
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//        systemUnderTest = PoemsViewModel(
//            poemsInteractor,
//            PoemDataToWordsOccurrencesMapper(),
//            SortMapper()
//        )
//    }
//
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun `for a valid poem name success is returned`() = runTest {
//        val successData = Outcome.Success(listOf(PoemData("", 1)))
//        `when`(poemsInteractor.getPoemData(anyString())).thenReturn(successData)
//
//        assertThat(systemUnderTest.stateLiveData.getOrAwaitValue()).isInstanceOf(State.Content::class.java)
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun `for a wrong poem name error is returned`() = runTest {
//        `when`(poemsInteractor.getPoemData(anyString())).thenThrow(RuntimeException())
//
//        assertThat(systemUnderTest.stateLiveData.getOrAwaitValue()).isInstanceOf(State.Error::class.java)
//    }

//}

