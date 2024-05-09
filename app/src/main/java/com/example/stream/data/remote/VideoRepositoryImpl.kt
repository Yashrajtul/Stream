package com.example.stream.data.remote

import com.example.stream.data.remote.dto.VideoDTO
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class VideoRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage
): VideoRepository {
    override suspend fun getVideos(): List<VideoDTO> {
        return withContext(Dispatchers.IO){
            val result = postgrest.from("Videos")
                .select().decodeList<VideoDTO>()
            result
        }
    }

    override suspend fun getVideo(
        name: String
    ): String {
        return withContext(Dispatchers.IO){
            val result = storage["Videos"].createSignedUrl("$name.mp4", expiresIn = 1.hours)
            result
        }
    }

}