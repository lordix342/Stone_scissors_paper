package com.pride.stonescissorspaper

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.pride.stonescissorspaper.viewModel.GameEvent

class MainActivity : AppCompatActivity() {
    private val gameVM: GameEvent by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val bexit = findViewById<Button>(R.id.bexit)
        val binfo = findViewById<Button>(R.id.binstruction)
        var activInfo: Boolean = false
        gameVM.activInfoForUse.observe(this) {
            activInfo = it
        }
        bexit.setOnClickListener {
            finish()
        }
        binfo.setOnClickListener {
            if (!activInfo) {
                navController.navigate(R.id.action_gameFragment_to_infoFragment)
                gameVM.activateInfo()
            }
        }
    }
}