package com.example.postsparser.di

import androidx.lifecycle.ViewModel
import com.example.postsparser.presentation.PostInfoViewModel
import com.example.postsparser.presentation.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    @Binds
    fun bindPostsViewModel(impl: PostsViewModel): ViewModel

    @IntoMap
    @ViewModelKey(PostInfoViewModel::class)
    @Binds
    fun bindPostViewModel(impl: PostInfoViewModel): ViewModel




}