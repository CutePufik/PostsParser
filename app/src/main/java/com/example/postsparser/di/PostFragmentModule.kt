package com.example.postsparser.di

import com.example.postsparser.presentation.OnPostClickListener
import com.example.postsparser.presentation.PostAdapter
import com.example.postsparser.presentation.PostsFragment
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
class PostFragmentModule {

    @Provides
    fun providePostAdapter(listener: OnPostClickListener): PostAdapter {
        return PostAdapter(listener)
    }

    @Provides
    fun provideOnPostClickListener(fragment: PostsFragment): OnPostClickListener {
        return fragment
    }
}
