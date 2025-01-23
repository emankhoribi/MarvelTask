package com.digi.domain.entity.comicapi

data class ComicData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ComicResult>,
    val total: Int
)