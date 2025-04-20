package com.example.postsparser.di

import com.example.postsparser.presentation.PostsFragment
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(modules = [PostFragmentModule::class])
interface PostsFragmentSubcomponent {

    fun inject(fragment: PostsFragment)






        @Subcomponent.Factory
        interface Factory {
            fun create(
                @BindsInstance number : Int,
                @BindsInstance fragment: PostsFragment
            ): PostsFragmentSubcomponent
        }


}
