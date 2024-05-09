package com.example.stream.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.stream.data.Videos

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideoScreen(
    modifier: Modifier = Modifier,
    items: List<Videos>,
    searchQuery: String,
    onValueChange: (String) -> Unit,
    isLoading: Boolean,
    getVideos: () -> Unit
) {
    val listState = rememberLazyListState()
    val pullRefreshState = rememberPullRefreshState(isLoading, onRefresh = getVideos)
    Box(
        modifier = modifier
            .fillMaxSize()
            .scrollable(rememberScrollState(), Orientation.Vertical)
            .pullRefresh(pullRefreshState)
    ) {
        PullRefreshIndicator(
            refreshing = isLoading,
            state = pullRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(2f)
        )
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                TextField(
                    value = searchQuery,
                    onValueChange = { onValueChange(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(0.dp),
                    placeholder = {
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    shape = CircleShape,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Search",
                                modifier = Modifier.clickable { onValueChange("") }
                            )
                        }
                    }
                )

            }
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = listState,
                modifier = Modifier
                    .scrollable(
                        rememberScrollState(),
                        orientation = Orientation.Vertical
                    )
                    .pullRefresh(pullRefreshState)
            ) {
                itemsIndexed(items, key = { _, item -> item.id }) {index, it->
                    VideoCard(
                        modifier = Modifier.padding(vertical = 8.dp),
                        url = it.url,
                        title = it.title,
                        description = it.description,
                        channelName = it.channelName,
                        likes = it.likes
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun VideoListPreview() {
    VideoScreen(
        items = listOf(
            Videos(1, "title", "description", 5, "channelName", "likes"),
            Videos(1, "title", "description", 5, "channelName", "likes"),
            Videos(1, "title", "description", 5, "channelName", "likes"),
            Videos(1, "title", "description", 5, "channelName", "likes"),
        ),
        searchQuery = "",
        onValueChange = {},
        isLoading = true,
        getVideos = {}
    )
}