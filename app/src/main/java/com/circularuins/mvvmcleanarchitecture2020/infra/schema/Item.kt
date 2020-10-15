package com.circularuins.mvvmcleanarchitecture2020.infra.schema

import com.squareup.moshi.Json

data class Item(
    val id: String?,
    val name: String?,
    val status: String?,
    @Json(name = "num_likes")
    val numLikes: Int?,
    @Json(name = "num_comments")
    val numComments: Int?,
    val price: Int?,
    val photo: String?
)