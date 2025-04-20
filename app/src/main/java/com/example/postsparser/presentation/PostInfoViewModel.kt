package com.example.postsparser.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.postsparser.domain.GetPostByIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PostInfoViewModel @Inject constructor(
    private val useCase: GetPostByIdUseCase,
    private val id : Int,
) : ViewModel() {

    val state : Flow<PostState> = flow{
        Log.d("Retrofit", "Запрашиваю пост с ID: $id")

        useCase.execute(id)
            .map { PostState.Result(it) as PostState }
            .onStart { PostState.Loading }
            .collect{
                emit(it)
            }
    }


    init {

        Log.d("ViewModel",state.toString())
    }
}