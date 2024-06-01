package com.example.rickandmortyguide.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyguide.data.model.Character
import com.example.rickandmortyguide.databinding.ItemCharacterBinding
import com.example.rickandmortyguide.ui.MainViewModel
import com.example.rickandmortyguide.ui.character.CharacterFragmentDirections

class CharacterAdapter(
    private var characters: List<Character>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<CharacterAdapter.CharacterItemHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitCharacters(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    inner class CharacterItemHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.apply {
                val character: Character = character
                ivCharacter.load(character.image)
                tvCharacterName.text = character.name
                itemView.setOnClickListener {
                    val characterId: Int = character.id
                    val navController = Navigation.findNavController(root)
                    val direction = CharacterFragmentDirections.toCharacterDetailFragment(characterId)
                    navController.navigate(direction)
                }


                val anim_bg: AnimationDrawable = binding.statusBackground.background as AnimationDrawable
                anim_bg.setEnterFadeDuration(33)
                anim_bg.setExitFadeDuration(888)
                anim_bg.start()

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterItemHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterItemHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }
}