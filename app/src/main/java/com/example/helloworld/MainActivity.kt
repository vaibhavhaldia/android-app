package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworld.ui.theme.HelloworldTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HelloworldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    Fruitlist(modifier = Modifier.padding(innerPadding))
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

//data class Fruit(val name: String, val color: String)
//
//@Composable
//fun Fruitlist(modifier: Modifier = Modifier){
//    val fruits = listOf(
//        Fruit("Apple", "Red"),
//        Fruit("Banana", "Yellow"),
//        Fruit("Mango", "Orange"),
//        Fruit("Grapes", "Purple"),
//    )
//
//    LazyColumn(
//        modifier = modifier,
//        contentPadding = PaddingValues(16.dp),
//        verticalArrangement = Arrangement.spacedBy(10.dp)
//    ){
//        items(fruits){
//            fruit ->
//                FruitRow(fruit)
//        }
//    }
//}
//
//@Composable
//fun FruitRow(fruit: Fruit){
//    Card(modifier = Modifier.fillMaxSize()) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(fruit.name, style = MaterialTheme.typography.titleMedium)
//            Text(fruit.color, style = MaterialTheme.typography.bodyMedium)
//        }
//    }
//}


@Composable
fun Fruitlist(modifier: Modifier) {
    val fruits = remember { mutableStateListOf("Apple", "Banana", "Guava") }

    Column(modifier = modifier) {
        Button(
            onClick = { fruits.add("Fruit ${fruits.size + 1}") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Add item")
        }
        LazyColumn {
            items(fruits) { fruit ->
                Text(fruit, modifier = Modifier.padding(12.dp))
            }
        }
    }
}

//@Composable
//fun Fruitlist(modifier: Modifier){
//    val fruit = listOf("Apple", "Oconut", "Pine", "Almond")
//
//    LazyColumn(modifier= Modifier) {
//        items(fruit) {f ->
//            Text(
//                text = f,
//                style = MaterialTheme.typography.bodyLarge,
//                modifier = Modifier.padding(16.dp)
//            )
//
//        }
//    }
//}