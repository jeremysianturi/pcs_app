package com.pcs.userlist

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
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
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        val modifier = Modifier.padding(it)
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
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
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Timber.tag("UserListScreen").d("avatar value => %s", userItem.avatar)
            Image(
                painter = rememberAsyncImagePainter(model = userItem.avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = userItem.name.orEmpty(), style = MaterialTheme.typography.bodyMedium)
                Text(text = userItem.country.orEmpty(), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
