package com.example.kotartisan

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotartisan.ui.theme.KotArtisanTheme

class MainActivity : ComponentActivity() {
    private lateinit var startButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)

        startButton = findViewById(R.id.startButton)
        startButton.setOnClickListener { val intent = Intent(this@MainActivity, Drawing::class.java)
            startActivity(intent) }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotArtisanTheme {
        Greeting("Android")
    }
}