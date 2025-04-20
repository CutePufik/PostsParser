package com.example.postsparser.presentation

import com.example.postsparser.domain.Post

sealed class PostState {

    object Error : PostState()

    object Loading : PostState()

    data class Result(val post : Post) : PostState()


}