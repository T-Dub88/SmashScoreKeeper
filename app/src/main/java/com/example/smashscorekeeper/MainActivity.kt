package com.example.smashscorekeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smashscorekeeper.navigation.SetupNavGraph
import com.example.smashscorekeeper.ui.theme.SmashScoreKeeperTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmashScoreKeeperTheme {
                // Sets up the nav controller
                // Keeps track of backtrack and composable screens.
                navController = rememberNavController()
                
                Image(
                    painter = painterResource(id = R.drawable.smash_ball),
                    contentDescription = "Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                
                SetupNavGraph(navController = navController)

            }
        }
    }
}
