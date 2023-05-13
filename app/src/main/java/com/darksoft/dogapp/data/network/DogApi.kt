package com.darksoft.dogapp.data.network

import com.darksoft.dogapp.data.network.model.DogsRandomResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {

    @GET("breeds/image/random/{cantidad}")
    suspend fun getDogs(
        @Path("cantidad") cantidad: Int,
    ): Response<DogsRandomResponse>

}