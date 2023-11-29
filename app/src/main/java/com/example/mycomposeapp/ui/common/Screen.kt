package com.example.mycomposeapp.ui.common

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("home/{fishId}"){
        fun createRoute(fishId: String) = "home/$fishId"
    }
    object About : Screen("about")
}