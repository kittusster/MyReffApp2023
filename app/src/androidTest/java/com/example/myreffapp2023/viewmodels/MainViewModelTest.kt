package com.example.myreffapp2023.viewmodels

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.example.myreffapp2023.models.GetPhotosResponse
import com.example.myreffapp2023.models.PhotoModel
import com.example.myreffapp2023.network.MyApi
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    private lateinit var api: MyApi

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var context: Context

    @Before
    fun setUp() {
        context = mock()
        api = Mockito.mock(MyApi::class.java)
    }

    @Test
    fun getPhotos() = runBlocking {
        val photo = PhotoModel(1, 1, "1234", "5678", "1234")
        val response = arrayListOf<PhotoModel>() as GetPhotosResponse
        response.add(photo)
        Mockito.`when`(api.getPhotos()).thenReturn(response)
        val photos = api.getPhotos()
        Truth.assertThat(photos).contains(photo)
    }
}