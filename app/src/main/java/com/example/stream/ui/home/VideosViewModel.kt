package com.example.stream.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stream.data.Videos
import com.example.stream.data.remote.VideoRepository
import com.example.stream.data.remote.dto.VideoDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val videoRepository: VideoRepository
) : ViewModel() {
    private val _allVideos = MutableStateFlow<List<Videos>>(emptyList())

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState>
        get() = _searchState.asStateFlow()

    init {
        getVideos()
    }

    fun getVideos() {
        viewModelScope.launch {
            val videos = videoRepository.getVideos()
            _allVideos.value = videos.map { it.asDomainModel() }
            _searchState.update {
                it.copy(
                    videos = _allVideos.value,
                    isLoading = false
                )
            }
        }
    }

    fun onValueChange(query: String) {
        _searchState.update {
            it.copy(
                query = query,
                videos = if (query.length >= 3) _allVideos.value.filter { video ->
                    video.doesMatchSearchQuery(searchState.value.query)
                } else _allVideos.value
            )
        }
    }

    private suspend fun VideoDTO.asDomainModel(): Videos {
        return Videos(
            id = this.id,
            title = this.title,
            description = this.description,
            likes = this.likes,
            channelName = this.channelName,
            url = videoRepository.getVideo(this.title)
        )
    }
}

data class SearchState(
    val query: String = "",
    val videos: List<Videos> = emptyList(),
    val isLoading: Boolean = true
)