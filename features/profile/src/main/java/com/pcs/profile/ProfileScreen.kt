package com.pcs.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.pcs.designsystem.component.ScaffoldTopAppbar
import com.pcs.entity.UserItemEntity

@Composable
internal fun ProfileScreenRoute(
    userItem: UserItemEntity,
    onBackBtnClick: () -> Unit
) {

    ProfileScreen(
        userItem = userItem,
        onBackBtnClick = onBackBtnClick
    )
}

@Composable
private fun ProfileScreen(
    userItem: UserItemEntity,
    onBackBtnClick: () -> Unit
) {
    ScaffoldTopAppbar(
        title = "Profile",
        onNavigationIconClick = onBackBtnClick
    ) {
        val modifier = Modifier.padding(it)
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            ProfileContentView(data = userItem)
        }
    }
}

@Composable
private fun ProfileContentView(
    data: UserItemEntity,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = data.avatar,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = data.name.orEmpty(), fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = data.county.orEmpty(), fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(8.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // Country
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Country: ", color = Color.Gray)
                    Text(
                        text = data.country.orEmpty(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Address
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Address: ", color = Color.Gray)
                    Text(
                        text = data.addressNo.orEmpty(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Street
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Street: ", color = Color.Gray)
                    Text(
                        text = data.street.orEmpty(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
