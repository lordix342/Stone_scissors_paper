package com.pride.stonescissorspaper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.pride.stonescissorspaper.databinding.FragmentInfoBinding
import com.pride.stonescissorspaper.viewModel.GameEvent


class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private val gameVM: GameEvent by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bunderstand.setOnClickListener {
            gameVM.deactivateInfo()
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_infoFragment_to_gameFragment)
        }
    }
}