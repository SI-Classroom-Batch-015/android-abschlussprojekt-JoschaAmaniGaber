package com.example.rickandmortyguide.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.rickandmortyguide.adapter.CharacterAdapter
import com.example.rickandmortyguide.databinding.FragmentCharacterBinding
import com.example.rickandmortyguide.ui.MainViewModel

class CharacterFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentCharacterBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.characters.observe(viewLifecycleOwner) { characters ->

            val adapter = CharacterAdapter(characters) { selectedCharacter ->
                val direction = CharacterFragmentDirections.toCharacterDetailFragment()
                findNavController().navigate(direction)
                viewModel.setSelectedCharacter(selectedCharacter)
            }

            binding.apply {
                rvCharacters.adapter = adapter
                rvCharacters.setHasFixedSize(true)
                rvCharacters.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }

            adapter.submitCharacters(characters)

        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}