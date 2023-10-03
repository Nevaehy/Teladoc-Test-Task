package com.example.teladoctesttask.ui.features.poems

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.teladoctesttask.domain.features.poems.PoemsInteractor
import com.example.teladoctesttask.domain.features.poems.PoemsInteractorImpl
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import com.example.teladoctesttask.R
import com.example.teladoctesttask.domain.base.Outcome
import com.example.teladoctesttask.domain.features.poems.models.PoemData
import com.example.teladoctesttask.helper.launchFragmentInHiltContainer
import com.example.teladoctesttask.presentation.features.poems.PoemsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.delay
import org.junit.Before
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import javax.inject.Inject
import javax.inject.Named

//TODO: Hadn't enough time to setup these test fully :(
@MediumTest
@HiltAndroidTest
class PoemsFragmentIntegrationTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_interactor")
    lateinit var poemsInteractor: PoemsInteractor

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
   // @Test
    fun onSortByFrequencyClick_AppropriateSortingInInteractorCalled() = runTest {
        val success = Outcome.Success(listOf(PoemData("ok", 1)))
        `when`(poemsInteractor.getPoemData(anyString())).thenReturn(success)
        val scenario = launchFragmentInHiltContainer<PoemsFragment>()
        onView(withId(R.id.ivFreqSort)).perform(click())

        verify(poemsInteractor).getSortedPoemData(PoemsInteractorImpl.Sort.FREQUENCY)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
   // @Test
    fun onSortAlphabeticallyClick_AppropriateSortingInInteractorCalled() = runTest {
        val scenario = launchFragmentInHiltContainer<PoemsFragment>()
        onView(withId(R.id.ivFreqSort)).perform(click())

        verify(poemsInteractor).getSortedPoemData(PoemsInteractorImpl.Sort.FREQUENCY)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
  //  @Test
    fun onSortByWordLengthClick_AppropriateSortingInInteractorCalled() = runTest {
        val scenario = launchFragmentInHiltContainer<PoemsFragment>()
        onView(withId(R.id.ivFreqSort)).perform(click())

        verify(poemsInteractor).getSortedPoemData(PoemsInteractorImpl.Sort.FREQUENCY)
    }
}