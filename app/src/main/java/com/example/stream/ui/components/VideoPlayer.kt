package com.example.stream.ui.components

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(
    url: String,
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.parse(url)))
            prepare()
        }
    }
//    val playbackState by exoPlayer.rememberPlaybackState()
//    val isPlaying = playbackState?.isPlaying ?: false

    AndroidView(

        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
    )

//    IconButton(
//        onClick = {
//            if (isPlaying) {
//                exoPlayer.pause()
//            } else {
//                exoPlayer.play()
//            }
//        },
//        modifier = Modifier
//            .padding(16.dp)
//            .align(Alignment.BottomEnd)
//    ) {
//        Icon(
//            imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
//            contentDescription = if (isPlaying) "Pause" else "Play",
//            tint = Color.White,
//            modifier = Modifier.size(48.dp)
//        )
//    }
}

@Preview(showSystemUi = true)
@Composable
private fun VideoPlayerPreview() {
    VideoPlayer(
        "https://aejxuyoyuiiyucccubfv.supabase.co/storage/v1/object/public/Videos/Message.mp4?t=2024-05-08T09%3A33%3A27.709Z",
    )
}