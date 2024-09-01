package com.pcs.userlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val userListScreenRoute = "userListScreenRoute"

fun NavGraphBuilder.userListScreen(
    onUserItemClick:(String)-> Unit
){
    composable(route = userListScreenRoute){
        UserListRoute(
            onUserItemClick = onUserItemClick
        )
    }
}