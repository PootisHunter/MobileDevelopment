package com.example.myapplication

import android.content.SharedPreferences
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.LineHeightStyle
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.delay


lateinit var user : String
lateinit var Uri : String

@Composable
fun MainScreen(navController: NavController, sharedPreferences: SharedPreferences){
    var text by rememberSaveable { mutableStateOf("") }
    var uriString by rememberSaveable{mutableStateOf("")}
    text = sharedPreferences.getString("username", "Username").toString()
    Uri = sharedPreferences.getString("profilePictureUri", "").toString()
    uriString = Uri
    val painter = rememberAsyncImagePainter(
       if(uriString.isEmpty())
            R.drawable.stick_figure_head1.toString()
       else
            uriString
    )
    val pickMedia = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            uriString = it.toString()
            updateUri(uri = uriString, sharedPreferences =  sharedPreferences)
        }
    }
    updateUser(text,sharedPreferences)
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        Image(
                painter = painter,
        contentDescription = null,
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
            .clickable { pickMedia.launch("image/*") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = text,
            onValueChange = {
                text = it
                updateUser(text,sharedPreferences)
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(){
            Button(
                    onClick = {
                        navController.navigate(Screen.Detailscreen.route)
                    }
            ){
            Text(text = "To messages")
            }
        }
    }
}

fun updateUser(name : String,sharedPreferences: SharedPreferences){
    user = name
    with (sharedPreferences.edit()) {
        putString("username", name)
        apply()
    }
}

fun updateUri(uri : String,sharedPreferences: SharedPreferences){
    Uri = uri
    with(sharedPreferences.edit()){
        putString("profilePictureUri", Uri)
        apply()
    }
}