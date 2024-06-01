package com.example.rickandmortyguide.ui.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickandmortyguide.data.model.Character
import com.example.rickandmortyguide.databinding.FragmentCharacterBinding
import com.example.rickandmortyguide.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortyguide.ui.MainViewModel


class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()
    private val viewModel: MainViewModel by activityViewModels()
    private var character: Character? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        character = viewModel.getCharacterById(args.idCharacter)
        character?.let { character ->
            binding.apply {
                ivCharacterDetail.load(character.image)
                textView.text = character.name
            }
        }
    }

}