package com.example.trelloclone.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trelloclone.Screens.CreateBoardScreen
import com.example.trelloclone.Screens.HomeScreen
import com.example.trelloclone.Screens.IntroScreen
import com.example.trelloclone.Screens.ProfileScreen
import com.example.trelloclone.Screens.SettingsScreen
import com.example.trelloclone.Screens.SignInScreen
import com.example.trelloclone.Screens.SignUpScreen
import com.example.trelloclone.ViewModel.AuthViewModel

@Composable
fun navigation(authViewModel: AuthViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Intro.route ){
        composable( Screen.Intro.route ){
            IntroScreen(navController)
        }
        composable( Screen.SignIn.route ){
            SignInScreen(navController,authViewModel)
        }
        composable( Screen.SignUp.route ){
            SignUpScreen(navController,authViewModel)
        }
        composable( Screen.Home.route ){
            HomeScreen(navController,authViewModel)
        }
        composable( Screen.DrawerScreen.MyProfile.route ){
            ProfileScreen(navController)
        }
        composable( Screen.DrawerScreen.Settings.route ){
            SettingsScreen(navController)
        }
        composable(Screen.CreateBoard.route){
            CreateBoardScreen(navController)
        }
    }
}