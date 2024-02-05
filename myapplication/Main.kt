package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val sharedPref = getSharedPreferences("Appdata", Context.MODE_PRIVATE)
            Navigation(sharedPref)
        }
    }
}
