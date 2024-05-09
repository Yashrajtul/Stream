package com.example.stream.di

import android.content.res.Resources
import com.example.stream.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SupabaseModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://aejxuyoyuiiyucccubfv.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFlanh1eW95dWlpeXVjY2N1YmZ2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ5OTc3NzAsImV4cCI6MjAzMDU3Mzc3MH0.2Z-4GVJjmrFdmFaeXKwftEZhFFSzqqUaB8s4MPWVKRE"
        ) {
            install(Postgrest)
            install(Storage)
        }
    }
    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest {
        return client.postgrest
    }
    @Provides
    @Singleton
    fun provideSupabaseStorage(client: SupabaseClient): Storage {
        return client.storage
    }
}