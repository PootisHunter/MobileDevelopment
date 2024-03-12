package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Preview
@Composable
fun Navigation(sharedPreferences: SharedPreferences, context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Mainscreen.route){
        composable(route = Screen.Mainscreen.route){
            MainScreen(navController = navController, sharedPreferences, context)
        }
        composable(
            route = Screen.Detailscreen.route,

        ){
            Conversation(navController = navController, messages = SampleData.conversationSample, user)
        }
    }
}


