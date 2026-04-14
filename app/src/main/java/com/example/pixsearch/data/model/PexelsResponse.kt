package com.example.pixsearch.data.model

data class PexelsResponse(
    val photos: List<PexelsPhoto>
)

data class PexelsPhoto(
    val id: Int,
    val photographer: String,
    val src: PhotoSource
)

data class PhotoSource(
    val medium: String,
    val large: String
)