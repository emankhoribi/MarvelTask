package com.digi.domain.entity.comicapi

data class ComicsResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: ComicData,
    val etag: String,
    val status: String
)