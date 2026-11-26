package com.example.helloworld

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.ui.theme.HelloworldTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue

data class Game(
    val id: Int,
    val name: String,       // text
    val genre: String,      // text
    val rating: Double,     // decimal number
    val isFavorite: Boolean, // true / false
    val emoji: String,       // a single emoji, still just a String
    @DrawableRes val imageRes: Int
)

fun sampleGames() = listOf(
    Game(1, "Cricket", "Sports", 4.8, true, "🏏", R.drawable.data1),
    Game(2, "Carrom",   "Board",    4.5, false, "🎯", R.drawable.darwin),
    Game(3, "Chess",    "Strategy", 4.9, true,  "♟️", R.drawable.ic_launcher_foreground),
    Game(4, "बैडमिंटन", "Sports",   4.2, false, "🏸", R.drawable.data1),
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
fun GameList(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = viewModel()
){
//    val games = remember { sampleGames().toMutableStateList() }
    val games by viewModel.games.collectAsState()
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(games, key = { it.id }) { game ->
            GameRow(
                game = game,
                onClick = {
                    Toast.makeText(context, "${game.name} clicked!", Toast.LENGTH_SHORT).show()
                },
                onDelete = {viewModel.deleteGame(game.id)}
            )
        }
    }
}

@Composable
fun GameRow(
    game: Game,
    onClick: () -> Unit,
    onDelete: () -> Unit,
){
    Card(modifier = Modifier
        .fillMaxSize()
        .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(game.emoji, fontSize = 32.sp)
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete ${game.name}"
                )
            }
            Image(painter = painterResource(game.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
                )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(game.name, style = MaterialTheme.typography.titleMedium)
                Text("${game.genre} • ⭐ ${game.rating}", style = MaterialTheme.typography.bodyMedium)
            }
            if (game.isFavorite) Text("❤\uFE0F", fontSize = 24.sp)
        }
    }
}