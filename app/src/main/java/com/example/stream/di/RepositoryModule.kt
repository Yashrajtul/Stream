package com.example.stream.di

import com.example.stream.data.remote.VideoRepository
import com.example.stream.data.remote.VideoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesVideoRepository(videoRepositoryImpl: VideoRepositoryImpl): VideoRepository
}