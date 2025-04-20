package com.example.postsparser.di

import dagger.Component


@Component(modules = [DomainModule::class, DataModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun activityComponentFactory(): PostsFragmentSubcomponent.Factory

    fun postInfoFactory() : PostInfoSubcomponent.Factory


}
