package com.digi.domain.entity

data class CharactersResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val _data: Data,
    val etag: String,
    val status: String
)