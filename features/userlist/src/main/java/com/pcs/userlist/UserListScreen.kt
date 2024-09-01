package com.pcs.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.pcs.designsystem.component.ScaffoldTopAppbar
import com.pcs.entity.UserItemEntity
import com.pcs.ui.component.NetworkErrorMessage
import timber.log.Timber

@Composable
internal fun UserListRoute(
    viewModel: UserListViewModel = hiltViewModel(),
    onUserItemClick: (String) -> Unit
) {
    val userListUiSate by viewModel.userListUiState.collectAsStateWithLifecycle()

    UserListScreen(
        userListUiSate = userListUiSate,
        onUserItemClick = onUserItemClick,
        onRefreshUserList = viewModel::handleAction
    )
}

@Composable
fun UserListScreen(
    userListUiSate: UserListUiState,
    onUserItemClick: (String) -> Unit,
    onRefreshUserList: (UserListUiAction) -> Unit
) {

    ScaffoldTopAppbar(
        title = "User List",
        containerColor = Color.Black
    ) {
        val modifier = Modifier.padding(it)
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF1E3C72), // Dark Blue
                            Color(0xFF2A5298)  // Lighter Blue
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            when (userListUiSate) {
                is UserListUiState.Error -> {
                    NetworkErrorMessage(
                        message = userListUiSate.message,
                        onClickRefresh = {
                            onRefreshUserList(UserListUiAction.FetchUserList)
                        }
                    )
                }
                is UserListUiState.HasUserList -> {
                    LazyColumn(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(items = userListUiSate.userList) { userItem ->
                            UserListItem(
                                userItem = userItem,
                                onItemClick = onUserItemClick
                            )
                        }
                    }
                }
                UserListUiState.Loading -> CircularProgressIndicator()
                UserListUiState.UserListEmpty -> Text(text = "No User List Found")
            }
        }
    }
}

@Composable
private fun UserListItem(
    modifier: Modifier = Modifier,
    userItem: UserItemEntity,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(Gson().toJson(userItem)) }
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // Set to transparent to show gradient
        )
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFB0E0E6), // Light Blue
                            Color(0xFF4682B4)  // Steel Blue
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Timber.tag("UserListScreen").d("avatar value => %s", userItem.avatar)
                AsyncImage(
                    model = userItem.avatar,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(10))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = userItem.name.orEmpty(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = userItem.createdAt.orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
