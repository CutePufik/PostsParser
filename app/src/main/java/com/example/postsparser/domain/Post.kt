package com.example.postsparser.domain

import javax.inject.Inject

data class Post@Inject constructor(
    val userId : Int,
    val id : Int,
    val title : String,
    val body : String,
)
