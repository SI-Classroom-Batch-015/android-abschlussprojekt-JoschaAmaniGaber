package com.example.rickandmortyguide.ui.characterdetail

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.rickandmortyguide.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortyguide.ui.MainViewModel

class CharacterDetailFragment: Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animIvBg: AnimationDrawable = binding.root.background as AnimationDrawable
        val animDetailStatus: AnimationDrawable = binding.animChDetailStatus.background as AnimationDrawable

        val animList: List<AnimationDrawable> = listOf(animIvBg, animDetailStatus)
        repeat(animList.size) {
            animList[it].setEnterFadeDuration(3333)
            animList[it].setExitFadeDuration(3333)
            animList[it].start()
        }

        viewModel.selectedCharacter.observe(viewLifecycleOwner) { selectedCharacter ->

            selectedCharacter?.let { character ->
                binding.apply {
                    ivChDetail.load(character.image)
                    ivChDetailBackground.load(character.image)
                    tvChDetailName.text = character.name
                    tvChDetailSpecies.text = character.species
                    tvType.text = character.type
                }
            }
        }


    }
}