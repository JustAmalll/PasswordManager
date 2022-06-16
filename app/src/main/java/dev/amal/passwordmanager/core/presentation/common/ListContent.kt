package dev.amal.passwordmanager.core.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import dev.amal.passwordmanager.core.domain.models.PasswordItem
import dev.amal.passwordmanager.core.presentation.ui.theme.TextGray
import dev.amal.passwordmanager.core.sharedViewModel.SharedViewModel
import dev.amal.passwordmanager.navigation.Screen
import kotlinx.coroutines.launch
import java.util.*

@ExperimentalMaterialApi
@Composable
fun ListContent(
    items: LazyPagingItems<PasswordItem>,
    navController: NavHostController,
    modalBottomSheetState: ModalBottomSheetState,
    sharedViewModel: SharedViewModel
) {
    if (items.itemCount == 0)
        EmptyContent()
    else {
        LazyColumn(
            modifier = Modifier.padding(top = 14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(items) { password ->
                password?.let {
                    Item(
                        item = password,
                        navController = navController,
                        modalBottomSheetState = modalBottomSheetState,
                        sharedViewModel = sharedViewModel
                    )
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun Item(
    item: PasswordItem,
    navController: NavHostController,
    modalBottomSheetState: ModalBottomSheetState,
    sharedViewModel: SharedViewModel
) {

    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.DetailsScreen.passItemId(itemId = item.id))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .padding(start = 14.dp)
                .size(44.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = item.title.take(2)
                        .replaceFirstChar { it.titlecase(Locale.getDefault()) },
                    maxLines = 1,
                    fontSize = 12.sp
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    color = Color.Black,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = item.email, maxLines = 1, color = TextGray, fontSize = 14.sp)
            }
            IconButton(
                modifier = Modifier.padding(end = 10.dp),
                onClick = {
                    sharedViewModel.onSelectedItem(item)
                    focusManager.clearFocus()
                    scope.launch { modalBottomSheetState.show() }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = Color.Gray
                )
            }
        }
    }
}