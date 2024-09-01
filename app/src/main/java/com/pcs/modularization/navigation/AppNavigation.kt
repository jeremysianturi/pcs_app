package com.pcs.modularization.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pcs.profile.navigateToProfileScreen
import com.pcs.profile.profileScreen
import com.pcs.userlist.userListScreen
import com.pcs.userlist.userListScreenRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:String = userListScreenRoute
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        userListScreen(onUserItemClick = { userJson ->
            navController.navigateToProfileScreen(userJson)
        })
        profileScreen(onBackBtnClick = navController::popBackStack)
    }
}