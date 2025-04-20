package com.example.postsparser.presentation

import com.example.postsparser.domain.Post


sealed class State {

    data object Error : State()

    data object Progress : State()

    data class Result(val list : List<Post>) : State()

}

