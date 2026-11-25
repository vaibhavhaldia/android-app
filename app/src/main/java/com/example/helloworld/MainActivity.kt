package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.ui.theme.HelloworldTheme

data class Game(
    val name: String,       // text
    val genre: String,      // text
    val rating: Double,     // decimal number
    val isFavorite: Boolean, // true / false
    val emoji: String       // a single emoji, still just a String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HelloworldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Vaibhav",
                        modifier = Modifier.padding(innerPadding)
                    )
                    GameList(modifier = Modifier.padding(innerPadding))
                }
            }
        }
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
    HelloworldTheme {
        Greeting("Android")
    }
}

@Composable
fun GameList(modifier: Modifier = Modifier){
    val games = listOf(
        Game("Cricket", "Sports", 4.8, true, "🏏"),
        Game("Carrom",   "Board",    4.5, false, "🎯"),
        Game("Chess",    "Strategy", 4.9, true,  "♟️"),
        Game("बैडमिंटन", "Sports",   4.2, false, "🏸"),
    )

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(games) { game ->
            GameRow(game)
        }
    }
}

@Composable
fun GameRow(game: Game){
    Card(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(game.emoji, fontSize = 32.sp)
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(game.name, style = MaterialTheme.typography.titleMedium)
                Text("${game.genre} • ⭐ ${game.rating}", style = MaterialTheme.typography.bodyMedium)
            }
            if (game.isFavorite) Text("❤\uFE0F", fontSize = 24.sp)
        }
    }
}