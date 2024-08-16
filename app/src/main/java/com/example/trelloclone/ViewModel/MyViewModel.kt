package com.example.trelloclone.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.trelloclone.Navigation.Screen

class MyViewModel : ViewModel() {
    private val _currentScreen: MutableState<Screen.DrawerScreen> = mutableStateOf(Screen.DrawerScreen.MyProfile)

    val currentScreen: MutableState<Screen.DrawerScreen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen.DrawerScreen){
        _currentScreen.value = screen
    }



}