package com.pride.stonescissorspaper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
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
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.anim)
        anim.interpolator = LinearInterpolator()
        anim.repeatCount = 0

        binding.apply {
            binstruction.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(R.id.action_gameFragment_to_infoFragment)
            }
            bexit.setOnClickListener {
                activity?.finish()
            }
            brock.setOnClickListener {
                gameVM.runGame(R.drawable.rock)
                binding.result.visibility = View.GONE
            }
            bpaper.setOnClickListener {
                gameVM.runGame(R.drawable.paper)
                binding.result.visibility = View.GONE
            }
            bsiccors.setOnClickListener {
                gameVM.runGame(R.drawable.siccors)
                binding.result.visibility = View.GONE
            }
        }
        gameVM.resIdImageForUse.observe(viewLifecycleOwner) {
            if (it!=null) {
                binding.gameselection.startAnimation(anim)
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        binding.gameselection.clearAnimation()
                    }

                    override fun onAnimationRepeat(p0: Animation?) {
                        binding.gameselection.clearAnimation()
                    }
                })
                binding.gameselection.setImageResource(it)
            }

        }
        gameVM.gameResult.observe(viewLifecycleOwner) {
            if (it != null) showResult(it)

        }
    }

    private fun showResult(resId: Int) {
        binding.imageResult.setImageResource(resId)
        binding.result.visibility = View.VISIBLE
        val animRes = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_result)
        animRes.interpolator = LinearInterpolator()
        animRes.repeatCount = 0
        binding.imageResult.startAnimation(animRes)
        animRes.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.imageResult.clearAnimation()
            }

            override fun onAnimationRepeat(p0: Animation?) {
                binding.imageResult.clearAnimation()
            }
        })

    }
}