package com.example.myapplication

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter


data class Message(val body: String)


@Composable
fun MessageCard(auth: String ,msg: Message, painter: Painter) {

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painter,
            contentDescription = "profile picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        Column(modifier = Modifier.clickable {isExpanded = !isExpanded}) {
            Text(
                text = auth,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium

                )
            }

        }
    }
}

@Composable
fun Conversation(navController: NavController,messages: List<Message>, author : String){
    val painter = rememberAsyncImagePainter(
        if(Uri.isEmpty()) {
            R.drawable.stick_figure_head1
        } else {
            Uri
        }
    )
    Column(){
        Box(modifier = Modifier.weight(0.1f)) {
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text(text = "To HomeScreen")
            }
        }
        Box(modifier = Modifier.weight(0.9f)){
            LazyColumn{
                items(messages) { message -> MessageCard(author,message,painter)
                }
            }
        }
    }
}
