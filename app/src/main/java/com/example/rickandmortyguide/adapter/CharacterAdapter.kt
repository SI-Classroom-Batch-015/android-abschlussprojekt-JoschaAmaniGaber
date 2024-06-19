package com.example.rickandmortyguide.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyguide.data.model.character.Character
import com.example.rickandmortyguide.databinding.ItemCharacterBinding

class CharacterAdapter(
    private var characters: List<Character>,
    private val onSelectCharacter: (Character) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterItemHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitCharacters(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
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

    inner class CharacterItemHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) = binding.apply {
            val character: Character = character
            ivCharacter.load(character.image)
            tvCharacterName.text = character.name

            clItemClickable.setOnClickListener {
                onSelectCharacter(character)
            }

            val anim_status: AnimationDrawable = binding.root.background as AnimationDrawable
            anim_status.setEnterFadeDuration(8)
            anim_status.setExitFadeDuration(3333)
            anim_status.start()

            val anim_bg: AnimationDrawable = binding.statusBackground.background as AnimationDrawable
            anim_bg.setEnterFadeDuration(8)
            anim_bg.setExitFadeDuration(3333)
            anim_bg.start()

            }


        }

}