package com.example.postsparser.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase@Inject constructor(private val postsRepository: PostsRepository){

    suspend fun execute() : Flow<List<Post>> = postsRepository.getPosts()
}