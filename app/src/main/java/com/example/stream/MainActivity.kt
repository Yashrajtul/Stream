package com.example.stream

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.stream.ui.home.VideoScreen
import com.example.stream.ui.home.VideosViewModel
import com.example.stream.ui.theme.StreamTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StreamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<VideosViewModel>()
    val state = viewModel.searchState.collectAsStateWithLifecycle().value
    VideoScreen(
        items = state.videos,
        searchQuery = state.query,
        onValueChange = viewModel::onValueChange,
        isLoading = state.isLoading,
        getVideos = viewModel::getVideos
    )
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
