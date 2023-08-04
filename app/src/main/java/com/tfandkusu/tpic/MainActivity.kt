package com.tfandkusu.tpic

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.tfandkusu.tpic.ui.theme.MyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Color.TRANSPARENT
        } else {
            Color.argb((255 * 0.5).toInt(), 0, 0, 0)
        }
        setContent {
            MyTheme {
                MainScreen()
            }
        }
    }
}
