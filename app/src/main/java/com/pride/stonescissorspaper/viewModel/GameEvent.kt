package com.pride.stonescissorspaper.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pride.stonescissorspaper.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameEvent(application: Application) : AndroidViewModel(application) {


    private var _resIdImageForUse: MutableLiveData<Int> = MutableLiveData()
    private var _gameResult: MutableLiveData<Int> = MutableLiveData()
    private var position: Int = 0
    private val gameChoices: Array<Int> =
        arrayOf(R.drawable.siccors, R.drawable.rock, R.drawable.paper)
    var resIdImageForUse: MutableLiveData<Int> = MutableLiveData()
    var gameResult: MutableLiveData<Int> = MutableLiveData()

    fun runGame(playerChoice: Int) {
        val randomNumb = (7..15).random()
        viewModelScope.launch {
            for (i in 0..randomNumb) {
                delay(200L)
                if (position >= 3) position = 0
                _resIdImageForUse.value = gameChoices[position]
                position++
                resIdImageForUse.value = _resIdImageForUse.value
            }
            when (playerChoice) {
                R.drawable.siccors -> when (resIdImageForUse.value) {
                    R.drawable.siccors -> _gameResult.value = R.string.result_draw
                    R.drawable.rock -> _gameResult.value = R.string.result_lose
                    R.drawable.paper -> _gameResult.value = R.string.result_win
                }
                R.drawable.rock -> when (resIdImageForUse.value) {
                    R.drawable.siccors -> _gameResult.value = R.string.result_win
                    R.drawable.rock -> _gameResult.value = R.string.result_draw
                    R.drawable.paper -> _gameResult.value = R.string.result_lose
                }
                R.drawable.paper -> when (resIdImageForUse.value) {
                    R.drawable.siccors -> _gameResult.value = R.string.result_lose
                    R.drawable.rock -> _gameResult.value = R.string.result_win
                    R.drawable.paper -> _gameResult.value = R.string.result_draw
                }
            }
            gameResult.value = _gameResult.value
        }
    }
}