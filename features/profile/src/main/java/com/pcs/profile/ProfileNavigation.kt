package com.pcs.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.pcs.entity.UserItemEntity
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val profileScreenRoute = "profileScreenRoute"

fun NavController.navigateToProfileScreen(userJson: String){
    val encodedJson = URLEncoder.encode(userJson, StandardCharsets.UTF_8.toString())
    navigate("$profileScreenRoute/$encodedJson")
}

fun NavGraphBuilder.profileScreen(
    onBackBtnClick: () -> Unit
) {
    composable(
        route = "$profileScreenRoute/{userJson}",
        arguments = listOf(navArgument("userJson") { defaultValue = "" })
    ) { backStackEntry ->
        val encodedJson = backStackEntry.arguments?.getString("userJson")
        val userJson = encodedJson?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) }
        val userItem = Gson().fromJson(userJson, UserItemEntity::class.java)
        ProfileScreenRoute(
            userItem = userItem,
            onBackBtnClick = onBackBtnClick
        )
    }
}