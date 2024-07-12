package com.example.coroutineproject.data

import com.example.coroutineproject.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserRetrofit {
    @Provides
    @Singleton
    fun providerRetrofit(): UserService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }



    @Provides
    @Singleton
    fun providerNewRepository(userService: UserService): UserRepository1 {
        return UserRepository1(userService)
    }
}