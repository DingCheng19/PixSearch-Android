package com.example.pixsearch.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixsearch.data.repository.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel (
    private val photoRepository: PhotoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun onSearchClick() {
        val query = _uiState.value.query

        if (query.isBlank()) return

        // ローディング開始
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {
            try {
                // 🔥 Repository経由でAPI呼び出し
                val photos = photoRepository.searchPhotos(query)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hasSearched = true,
                        photos = photos
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "検索に失敗しました"
                    )
                }
            }
        }
    }


    fun onRetryClick() {
        onSearchClick()
    }
}