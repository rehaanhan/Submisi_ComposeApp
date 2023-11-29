package com.example.mycomposeapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycomposeapp.R
import com.example.mycomposeapp.ui.common.NavigationItems
import com.example.mycomposeapp.ui.common.Screen
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

@Composable
fun FishApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold (
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController)
            }
        },
        modifier = Modifier
    ){
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(it)
        ){
            composable(Screen.Home.route){
                HomeIkan(navToDetail = { data ->
                    navController.navigate(Screen.Detail.createRoute(data))
                })
            }
            composable(Screen.About.route){
                ProfileIkan(onBackClick = {navController.navigateUp()})
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("fishId"){
                        type = NavType.StringType
                    }
                )
            ){
                val id = it.arguments?.getString("fishId") ?: ""
                DetailFish(
                    fishId = id,
                    navigateBack = {navController.navigateUp()}
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = Modifier,
    ) {
        val navItems = listOf(
            NavigationItems(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItems(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.About
            ),
        )
        navItems.map { item ->
            NavigationBarItem(
                icon ={
                  Icon(
                      imageVector = item.icon,
                      contentDescription = item.title )
                },
                label = { Text(item.title)},
                selected = false,
                onClick = { navController.navigate(item.screen.route){
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FishAppPreview(){
    MyComposeAppTheme {
        FishApp()
    }
}