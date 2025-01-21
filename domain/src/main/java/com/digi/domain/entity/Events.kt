package com.digi.domain.entity

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)