package com.example.myreffapp2023.repository

import com.example.myreffapp2023.models.PhotoModel
import com.example.myreffapp2023.network.MyApi
import javax.inject.Inject

class MyRepo @Inject constructor(private val myApi: MyApi) {

    suspend fun getPhotos() = myApi.getPhotos()
}