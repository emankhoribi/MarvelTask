package com.digi.domain.entity.comicapi

data class ComicResult(
    val description: String,
    val diamondCode: String,
    val digitalId: Int,
    val ean: String,
    val format: String,
    val id: Int,
    val isbn: String,
    val issn: String,
    val issueNumber: Int,
    val modified: String,
    val pageCount: Int,
    val resourceURI: String,
    val thumbnail: Thumbnail,
    val title: String,
    val upc: String,
    val variantDescription: String
)