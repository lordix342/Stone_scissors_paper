package com.pride.stonescissorspaper.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pride.stonescissorspaper.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameEvent(application: Application) : AndroidViewModel(application) {

    private var activInfoSaveArg: MutableLiveData<Boolean> = MutableLiveData()
    private var resIdImageSaveArg: MutableLiveData<Int> = MutableLiveData()
    private var gameResultSaveArg: MutableLiveData<Int> = MutableLiveData()
    private var position: Int = 0
    private val gamechoices: Array<Int> =
        arrayOf(R.drawable.siccors, R.drawable.rock, R.drawable.paper)

    var activInfoForUse: MutableLiveData<Boolean> = MutableLiveData()
    var resIdImageForUse: MutableLiveData<Int> = MutableLiveData()
    var gameResult: MutableLiveData<Int> = MutableLiveData()


    fun activateInfo() {
        activInfoSaveArg.value = true
        activInfoForUse = activInfoSaveArg
    }

    fun deactivateInfo() {
        activInfoSaveArg.value = false
        activInfoForUse = activInfoSaveArg
    }

    fun runGame(playerChoice: Int) {
        val randnumb = (7..15).random()
        viewModelScope.launch {
            for (i in 0..randnumb) {
                delay(200L)
                if (position >= 3) position = 0
                resIdImageSaveArg.value = gamechoices[position]
                position++
                resIdImageForUse.value = resIdImageSaveArg.value
            }
            when (playerChoice) {
                R.drawable.siccors -> when (resIdImageForUse.value) {
                    R.drawable.siccors -> gameResultSaveArg.value = R.string.result_draw
                    R.drawable.rock -> gameResultSaveArg.value = R.string.result_lose
                    R.drawable.paper -> gameResultSaveArg.value = R.string.result_win
                }
                R.drawable.rock -> when (resIdImageForUse.value) {
                    R.drawable.siccors -> gameResultSaveArg.value = R.string.result_win
                    R.drawable.rock -> gameResultSaveArg.value = R.string.result_draw
                    R.drawable.paper -> gameResultSaveArg.value = R.string.result_lose
                }
                R.drawable.paper -> when (resIdImageForUse.value) {
                    R.drawable.siccors -> gameResultSaveArg.value = R.string.result_lose
                    R.drawable.rock -> gameResultSaveArg.value = R.string.result_win
                    R.drawable.paper -> gameResultSaveArg.value = R.string.result_draw
                }
            }
            gameResult.value = gameResultSaveArg.value
        }
    }
}