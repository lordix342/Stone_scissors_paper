@file:Suppress("DEPRECATION")

package com.pride.stonescissorspaper

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pride.stonescissorspaper.databinding.FragmentGameBinding
import com.pride.stonescissorspaper.viewModel.GameEvent

class GameFragment : Fragment() {

    private val gameVM: GameEvent by viewModels()
    private lateinit var binding: FragmentGameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            brock.setOnClickListener {
                gameVM.runGame(R.drawable.rock)
            }
            bpaper.setOnClickListener {
                gameVM.runGame(R.drawable.paper)
            }
            bsiccors.setOnClickListener {
                gameVM.runGame(R.drawable.siccors)
            }
        }
        gameVM.resIdImageForUse.observe(viewLifecycleOwner) {
            binding.gameselection.setImageResource(it)
        }
        gameVM.gameResult.observe(viewLifecycleOwner) {
            if (it != null) showResult(it)

        }
    }

    private fun showResult(resId: Int) {
        val toast = Toast.makeText(requireContext(), resId, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        val textToast = toast.view?.findViewById<TextView>(android.R.id.message)
        textToast?.setTextColor(resources.getColor(R.color.black))
        textToast?.textSize = 34f
        textToast?.setBackgroundColor(resources.getColor(R.color.white))
        toast.show()
    }
}