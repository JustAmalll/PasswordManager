package dev.amal.passwordmanager.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.data.models.Password
import dev.amal.passwordmanager.navigation.Screen
import dev.amal.passwordmanager.ui.theme.TextGray
import dev.amal.passwordmanager.utils.RequestState
import java.util.*

@Composable
fun ListContent(
    items: RequestState<List<Password>>,
    navController: NavHostController
) {
    if (items is RequestState.Success) {
        if (items.data.isEmpty())
            EmptyContent()
        else {
            LazyColumn(
                contentPadding = PaddingValues(all = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                this.items(
                    items = items.data,
                    key = { item -> item.id }
                ) { item ->
                    Item(item = item, navController = navController)
                }
            }
        }
    }
}


@Composable
fun Item(
    item: Password,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 5.dp)
            .clickable {
                navController.navigate(Screen.Details.passItemId(itemId = item.id))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(modifier = Modifier.size(40.dp), shape = RoundedCornerShape(8.dp)) {
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
        Column {
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
    }
}