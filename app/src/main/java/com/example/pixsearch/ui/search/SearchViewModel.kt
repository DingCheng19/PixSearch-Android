package com.example.pixsearch.ui.search

import androidx.lifecycle.ViewModel
import com.example.pixsearch.data.repository.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel (
    private val photoRepository: PhotoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun onSearchClick() {
        // まずはダミー検索でもOK
        _uiState.update {
            it.copy(
                hasSearched = true,
                photos = listOf(
                    PhotoItemUiModel(
                        id = 1,
                        previewUrl = "https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg",
                        originalUrl = "https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg",
                        photographer = "John Doe"
                    ),
                    PhotoItemUiModel(
                        id = 2,
                        previewUrl = "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg",
                        originalUrl = "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg",
                        photographer = "Jane Smith"
                    )
                )
            )
        }
    }

    fun onRetryClick() {
        onSearchClick()
    }
}