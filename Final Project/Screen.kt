package com.example.myapplication

sealed class Screen(val route: String) {
    object Mainscreen : Screen("main_screen")
    object Detailscreen : Screen("detail_screen")
}