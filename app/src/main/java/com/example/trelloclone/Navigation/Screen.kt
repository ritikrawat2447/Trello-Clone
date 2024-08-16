package com.example.trelloclone.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen( val route : String ) {
    sealed class DrawerScreen(val dRoute: String, val dTitle: String, val Icon: ImageVector) : Screen( dRoute ){
        object MyProfile : DrawerScreen("profile_screen","My Profile", Icons.Default.PersonPin)
        object Settings : DrawerScreen("settings_screen","Settings", Icons.Default.Settings)
        object SignOut : DrawerScreen("signout_screen","Sign Out", Icons.Default.PowerSettingsNew)
    }
    object Intro : Screen("intro_screen")
    object SignIn : Screen("signin_screen")
    object SignUp : Screen("signup_screen")
    object Home : Screen("home_screen")
    object CreateBoard : Screen("create_board_screen")
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.MyProfile,
    Screen.DrawerScreen.Settings,
    Screen.DrawerScreen.SignOut
)