package com.example.rickandmortyguide.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.rickandmortyguide.data.remote.ApiStatus
import com.example.rickandmortyguide.databinding.FragmentStartBinding

class StartFragment: Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val viewModel: MainViewModel by activityViewModels()

    private var toast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            animRick.visibility = View.GONE
            ivRick.visibility = View.GONE
            button.visibility = View.VISIBLE
        }

        viewModel.loading.observe(viewLifecycleOwner) {

            when (it) {
                ApiStatus.LOADING -> binding.apply {
                        animRick.visibility = View.VISIBLE
                        animRick.playAnimation()
                        ivRick.visibility = View.GONE
                    }

                ApiStatus.DONE -> binding.apply {
                        animRick.pauseAnimation()
                        animRick.visibility = View.GONE
                        button.visibility = View.GONE
                        ivRick.visibility = View.VISIBLE
                    }

                ApiStatus.ERROR -> binding.apply {
                    binding.animRick.visibility = View.GONE
                    binding.ivRick.visibility = View.GONE
                    toast = Toast.makeText(requireContext(), "Could not load Data.", Toast.LENGTH_LONG)
                    toast?.show()
                }
            }
        }

        startAnimation()

        binding.apply {
            button.setOnClickListener {
                viewModel.loadCharacters()
            }
        }

        binding.apply {
            ivRick.setOnClickListener {
                val navController = Navigation.findNavController(root)
                val direction = StartFragmentDirections.toCharacterFragment()
                navController.navigate(direction)
            }
        }
    }

    private fun startAnimation() {
        val animHeader: AnimationDrawable = binding.ivRick.background as AnimationDrawable
        animHeader.setEnterFadeDuration(8)
        animHeader.setExitFadeDuration(888)
        animHeader.start()
    }
}