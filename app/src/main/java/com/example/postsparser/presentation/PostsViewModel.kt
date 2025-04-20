package com.example.postsparser.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.postsparser.domain.GetPostsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val useCase: GetPostsUseCase
) : ViewModel() {

    val state: Flow<State> = flow{
        useCase.execute()
            .map {State.Result(list = it) as State}
            .onStart { emit(State.Progress) }
            .onCompletion {
                Log.d("PostsViewModel","PostsViewModelPostsViewModelPostsViewModel")
            }
            .collect{
                emit(it)
            }
    }

}