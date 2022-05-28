package dev.amal.passwordmanager.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.amal.passwordmanager.data.models.Password
import dev.amal.passwordmanager.ui.theme.TextGray
import java.util.*

@Composable
fun BottomSheetContent(
    selectedItem: Password,
    onLaunchWebsite: (String) -> Unit,
    onCopyEmail: (String) -> Unit,
    onCopyPassword: (String) -> Unit,
    onEdit: (Password) -> Unit,
    onShare: () -> Unit,
    onDelete: (Password) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp, start = 14.dp, bottom = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(44.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = selectedItem.title.take(2)
                            .replaceFirstChar { it.titlecase(Locale.getDefault()) },
                        maxLines = 1,
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = selectedItem.title,
                    fontSize = 16.sp,
                    color = Color.Black,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = selectedItem.email,
                    fontSize = 16.sp,
                    color = TextGray,
                    maxLines = 1
                )
            }
        }
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        BottomSheetItem(
            imageVector = Icons.Default.ArrowForward,
            action = { onLaunchWebsite(selectedItem.website) },
            text = "Launch Website"
        )
        BottomSheetItem(
            imageVector = Icons.Default.AccountCircle,
            action = { onCopyEmail(selectedItem.email) },
            text = "Copy Email or Username"
        )
        BottomSheetItem(
            imageVector = Icons.Default.Add,
            action = { onCopyPassword(selectedItem.password) },
            text = "Copy Password"
        )
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        BottomSheetItem(
            imageVector = Icons.Default.Edit,
            action = { onEdit(selectedItem) },
            text = "Edit"
        )
        BottomSheetItem(
            imageVector = Icons.Default.Share,
            action = { onShare() },
            text = "Share"
        )
        BottomSheetItem(
            imageVector = Icons.Default.Delete,
            action = { onDelete(selectedItem) },
            text = "Delete"
        )
        Spacer(modifier = Modifier.padding(bottom = 6.dp))
    }
}

@Composable
fun BottomSheetItem(
    imageVector: ImageVector,
    action: () -> Unit,
    text: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { action() }
    ) {
        Row(
            modifier = Modifier.padding(start = 14.dp, top = 12.dp, bottom = 12.dp)
        ) {
            Icon(imageVector = imageVector, contentDescription = "Action")
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = text)
        }
    }
}