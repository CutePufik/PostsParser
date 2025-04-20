package com.example.postsparser.domain

import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(private val postsRepository: PostsRepository) {

    suspend fun execute(id : Int) = postsRepository.getPost(id)

}