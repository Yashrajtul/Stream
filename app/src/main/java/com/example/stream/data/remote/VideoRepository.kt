package com.example.stream.data.remote

import com.example.stream.data.remote.dto.VideoDTO
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

interface VideoRepository {

    suspend fun getVideos() : List<VideoDTO>

    suspend fun getVideo(
        name: String
    ) : String

}
