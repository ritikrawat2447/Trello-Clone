package com.example.trelloclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.trelloclone.Navigation.navigation
import com.example.trelloclone.ViewModel.AuthViewModel
import com.example.trelloclone.ui.theme.TrelloCloneTheme

class MainActivity : ComponentActivity() {
    val authViewModel : AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrelloCloneTheme {
                navigation(authViewModel)
            }
        }
    }
}