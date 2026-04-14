package com.example.pixsearch.data.mapper

import com.example.pixsearch.data.model.PexelsPhoto
import com.example.pixsearch.ui.search.PhotoItemUiModel

/**
 * API レスポンスを UI 表示用モデルに変換する
 */
fun PexelsPhoto.toUiModel(): PhotoItemUiModel {
    return PhotoItemUiModel(
        id = id,
        previewUrl = src.medium,
        originalUrl = src.large,
        photographer = photographer
    )
}