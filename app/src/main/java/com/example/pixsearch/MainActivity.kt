package com.example.pixsearch

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pixsearch.ui.detail.DetailScreen
import com.example.pixsearch.ui.search.SearchScreenRoute
import com.example.pixsearch.ui.theme.PixSearchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PixSearchTheme {
                PixSearchApp()
            }
        }
    }
}

@Composable
fun PixSearchApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") {
            SearchScreenRoute(
                onPhotoClick = { photo ->
                    val encodedImageUrl = Uri.encode(photo.originalUrl)
                    val encodedPhotographer = Uri.encode(photo.photographer)

                    navController.navigate(
                        "detail/$encodedImageUrl/$encodedPhotographer"
                    )
                }
            )
        }

        composable("detail/{imageUrl}/{photographer}") { backStackEntry ->
            val imageUrl =
                backStackEntry.arguments?.getString("imageUrl").orEmpty()
            val photographer =
                backStackEntry.arguments?.getString("photographer").orEmpty()

            DetailScreen(
                imageUrl = imageUrl,
                photographer = photographer,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}