package com.example.postsparser.domain

import kotlinx.coroutines.flow.Flow


interface PostsRepository{
    suspend fun getPosts() : Flow<List<Post>>

    suspend fun getPost(id : Int) : Flow<Post>
}