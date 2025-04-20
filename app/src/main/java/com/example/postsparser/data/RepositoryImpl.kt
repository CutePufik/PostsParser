package com.example.postsparser.data

import com.example.postsparser.domain.Post
import com.example.postsparser.domain.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : PostsRepository{

    override suspend fun getPosts(): Flow<List<Post>> {
        val posts = apiService.getPosts()
        return flowOf(posts)
    }

    override suspend fun getPost(id : Int): Flow<Post> = flow{
        emit(apiService.getPost(id))
    }



}