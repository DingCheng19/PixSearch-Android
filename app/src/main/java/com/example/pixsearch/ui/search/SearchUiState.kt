package com.example.pixsearch.ui.search

data class SearchUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val photos: List<PhotoItemUiModel> = emptyList(),
    val errorMessage: String? = null,
    val hasSearched: Boolean = false,
    val showRetry: Boolean = false
)

data class PhotoItemUiModel(
    val id: Int,
    val previewUrl: String,
    val originalUrl: String,
    val photographer: String
)