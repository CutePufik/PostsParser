package com.example.postsparser.di

import com.example.postsparser.presentation.PostInfoFragment
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent
interface PostInfoSubcomponent {


    fun inject(fragment : PostInfoFragment)

    @Subcomponent.Factory
    interface Factory{

        fun create(
            @BindsInstance id : Int
        ) : PostInfoSubcomponent
    }


}