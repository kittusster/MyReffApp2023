package com.example.myreffapp2023.network

import com.example.myreffapp2023.Constants.GET_PHOTOS
import com.example.myreffapp2023.models.GetPhotosResponse
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET(GET_PHOTOS)
    suspend fun getPhotos(): GetPhotosResponse
}