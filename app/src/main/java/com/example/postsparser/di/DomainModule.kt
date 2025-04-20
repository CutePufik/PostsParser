package com.example.postsparser.di

import com.example.postsparser.data.RepositoryImpl
import com.example.postsparser.domain.PostsRepository
import dagger.Binds
import dagger.Module


@Module
interface DomainModule {

    @Binds
    fun bindPostRepository(impl : RepositoryImpl) : PostsRepository

}