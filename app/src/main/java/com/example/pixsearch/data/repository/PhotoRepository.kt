package com.example.pixsearch.data.repository

import com.example.pixsearch.ui.search.PhotoItemUiModel

interface PhotoRepository {

    /**
     * 写真を検索する
     */
    suspend fun searchPhotos(query: String): List<PhotoItemUiModel>
}