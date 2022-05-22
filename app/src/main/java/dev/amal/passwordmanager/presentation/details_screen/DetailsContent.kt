package dev.amal.passwordmanager.presentation.details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.amal.passwordmanager.R
import dev.amal.passwordmanager.StandardToolbar
import dev.amal.passwordmanager.data.models.Password
import dev.amal.passwordmanager.ui.theme.BackGroundColor
import dev.amal.passwordmanager.ui.theme.TextGray
import java.util.*

@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedItem: Password
) {

    var showPassword by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            StandardToolbar(
                showBackArrow = true,
                onCloseClicked = { navController.popBackStack() },
                navActions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Save changes",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackGroundColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier.size(70.dp),
                        shape = RoundedCornerShape(12.dp),
                        backgroundColor = Color.DarkGray
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = selectedItem.title.take(2)
                                    .replaceFirstChar { it.titlecase(Locale.getDefault()) },
                                maxLines = 1,
                                fontSize = 23.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = selectedItem.title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 14.dp)
                        .padding(vertical = 14.dp)
                ) {
                    Text(text = "Email or Username", color = TextGray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = selectedItem.email)
                }
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 14.dp, end = 8.dp)
                        .padding(vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Password", color = TextGray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        if (showPassword) Text(
                            text = selectedItem.password,
                            fontWeight = FontWeight.Bold
                        )
                        else {
                            Row {
                                repeat(selectedItem.password.length) {
                                    Text(text = "•")
                                }
                            }
                        }
                    }
                    val image =
                        if (showPassword) painterResource(id = R.drawable.ic_visibility_off)
                        else painterResource(id = R.drawable.ic_visibility)

                    val description = if (showPassword) "Hide password" else "Show password"

                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(painter = image, description)
                    }
                }
                Divider()
                Column(
                    modifier = Modifier
                        .padding(start = 14.dp)
                        .padding(vertical = 14.dp)
                ) {
                    Text(text = "Website or App Name", color = TextGray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = selectedItem.website)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(start = 14.dp)
                        .padding(vertical = 14.dp),
                ) {
                    Text(
                        text = "Password Security",
                        color = TextGray,
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Strong Password",
                        color = Color.Green
                    )
                }
            }
        }
    }
}