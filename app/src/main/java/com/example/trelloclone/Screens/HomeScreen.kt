package com.example.trelloclone.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trelloclone.ViewModel.AuthState
import com.example.trelloclone.ViewModel.AuthViewModel
import com.example.trelloclone.ViewModel.MyViewModel
import com.example.trelloclone.R
import com.example.trelloclone.Navigation.Screen
import com.example.trelloclone.Navigation.screensInDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController, authViewModel: AuthViewModel) {
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val scope = rememberCoroutineScope()
    val isSheetFullScreen by remember { mutableStateOf(false) }

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val viewModel: MyViewModel = viewModel()

    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = remember {
        viewModel.currentScreen.value
    }

    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> {
                navController.navigate(Screen.Intro.route)
            }

            else -> Unit
        }
    }


//    val modalSheetState = androidx.compose.material.rememberModalBottomSheetState(
//        initialValue = ModalBottomSheetValue.Hidden,
//        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded}
//    )

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val title = remember {
        mutableStateOf(currentScreen.dTitle)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    Modifier.wrapContentSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable._564535_customer_user_userphoto_account_person_icon),
                        contentDescription = "profile pic",
                        modifier = Modifier
                            .size(130.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = "Ritik Rawat",
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Divider(
                    color = Color.DarkGray
                )
                var isSelected by remember {
                    mutableStateOf(false)
                }
                val context = LocalContext.current
                screensInDrawer.forEach { screen ->
                    NavigationDrawerItem(
                        icon = { Icon(screen.Icon, contentDescription = screen.dTitle) },
                        label = { Text(screen.dTitle) },
                        selected = isSelected,
                        onClick = {
                            when (screen) {
                                is Screen.DrawerScreen.SignOut -> {
                                    authViewModel.signOut()
                                }

                                else -> {
                                    navController.navigate(screen.dRoute)
                                }
                            }
                        },
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Trello") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu Icon")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.CreateBoard.route) },
                    shape = CircleShape,
                ) {
                    Icon(Icons.Filled.Add, "Localized description")
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                HomeCardView()
            }
        }
    }
}

@Composable
fun HomeCardView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

    }
}