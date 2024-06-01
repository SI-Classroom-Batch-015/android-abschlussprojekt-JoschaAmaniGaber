package com.example.rickandmortyguide.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.rickandmortyguide.adapter.CharacterAdapter
import com.example.rickandmortyguide.databinding.FragmentCharacterBinding
import com.example.rickandmortyguide.ui.MainViewModel

class CharacterFragment : Fragment() {

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

        val rvCharacters = binding.rvCharacters
        val characteradapter = CharacterAdapter(emptyList(), viewModel)
        rvCharacters.adapter = characteradapter

        /**viewModel.loading.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                ApiStatus.LOADING -> binding.progr
            }
        }
        */

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            val adapter = CharacterAdapter(characters, viewModel)
            binding.rvCharacters.adapter = adapter
            binding.rvCharacters.setHasFixedSize(true)
            binding.rvCharacters.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter.submitCharacters(characters)
        }
    }
}