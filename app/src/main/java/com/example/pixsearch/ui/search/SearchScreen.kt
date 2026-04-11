package com.example.pixsearch.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SearchScreenRoute() {
    val dummyState = SearchUiState(
        query = "cat",
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
        ),
        hasSearched = true
    )

    SearchScreen(
        uiState = dummyState,
        onQueryChange = {},
        onSearchClick = {},
        onPhotoClick = {},
        onRetryClick = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onPhotoClick: (PhotoItemUiModel) -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("PixSearch") },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            SearchBarSection(
                query = uiState.query,
                onQueryChange = onQueryChange,
                onSearchClick = onSearchClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                uiState.isLoading -> {
                    LoadingContent()
                }

                uiState.photos.isEmpty() && uiState.hasSearched -> {
                    EmptyContent()
                }

                uiState.photos.isEmpty() -> {
                    InitialContent()
                }

                else -> {
                    PhotoGrid(
                        photos = uiState.photos,
                        onPhotoClick = onPhotoClick
                    )
                }
            }

            if (uiState.showRetry) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onRetryClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("再試行")
                }
            }
        }
    }
}

@Composable
private fun SearchBarSection(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Column {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("キーワードを入力") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClick() }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onSearchClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = query.isNotBlank()
        ) {
            Text("検索")
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun InitialContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "キーワードを入力して写真を検索してください",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun EmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "検索結果が見つかりませんでした",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PhotoGrid(
    photos: List<PhotoItemUiModel>,
    onPhotoClick: (PhotoItemUiModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(photos) { photo ->
            PhotoGridItem(
                photo = photo,
                onClick = { onPhotoClick(photo) }
            )
        }
    }
}

@Composable
private fun PhotoGridItem(
    photo: PhotoItemUiModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column {
            AsyncImage(
                model = photo.previewUrl,
                contentDescription = photo.photographer,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = photo.photographer,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}