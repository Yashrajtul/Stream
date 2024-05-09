package com.example.stream.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stream.ui.components.VideoPlayer

@Composable
fun VideoCard(
    modifier: Modifier = Modifier,
    url: String,
    title: String,
    description: String,
    channelName: String,
    likes: Int,
    isPlaying: Boolean = false
) {
    var isPlayerUiVisible by remember { mutableStateOf(false) }
    val isPlayButtonVisible = if(isPlayerUiVisible) true else !isPlaying
    Card(
        modifier = modifier
            .fillMaxWidth(),
        onClick = { /*TODO*/ }
    ) {
        VideoPlayer(url = url)

//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(16 / 9f)
//                .background(Color.Black)
//        )
        Column(
            modifier = Modifier
                .height(110.dp)
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Icon(
                    imageVector = if (likes > 0) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.padding(end = 4.dp)
                )
                if (likes > 0)
                    Text(
                        text = likes.toString(),
                        color = Color.Red,
                        fontSize = 18.sp
                    )
            }
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically ,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = title,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = channelName,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                )

            }
            Text(
                text = description,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Gray
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun VideoListItemPreview() {
    VideoCard(
        url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        title = "Big Buck Bunny",
        description = "A cute bunny looking at the camera lahoihsdahdoi iaiosfh ohi jhsoia;hhoahd",
        channelName = "Big Bunny",
        likes = 12,
        isPlaying = false
    )
}