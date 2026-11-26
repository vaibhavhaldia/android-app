package com.example.helloworld

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {

    private val _games = MutableStateFlow(sampleGames())
    val games: StateFlow<List<Game>> = _games.asStateFlow()

    fun deleteGame(id: Int){
        _games.update { list -> list.filterNot {it.id == id} }
    }
}