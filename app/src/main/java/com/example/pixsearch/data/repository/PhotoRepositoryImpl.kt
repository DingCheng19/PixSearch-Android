package com.example.pixsearch.data.repository

import android.util.Log
import com.example.pixsearch.data.api.PexelsApiService
import com.example.pixsearch.data.mapper.toUiModel
import com.example.pixsearch.ui.search.PhotoItemUiModel

class PhotoRepositoryImpl(
    private val apiService: PexelsApiService
) : PhotoRepository {

    override suspend fun searchPhotos(query: String): List<PhotoItemUiModel> {
        Log.d(TAG, "searchPhotos 開始: query=$query")

        val response = apiService.searchPhotos(query = query)

        val result = response.photos.map { it.toUiModel() }

        Log.d(TAG, "searchPhotos 成功: 件数=${result.size}")

        return result
    }

    companion object {
        private const val TAG = "PhotoRepository"
    }
}