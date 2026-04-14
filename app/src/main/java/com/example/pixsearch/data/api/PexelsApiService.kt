package com.example.pixsearch.data.api

import com.example.pixsearch.data.model.PexelsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApiService {

    /**
     * Pexels の写真検索 API
     *
     * @param query 検索キーワード
     * @param perPage 1ページあたりの件数
     */
    @GET("v1/search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int = 20
    ): PexelsResponse
}