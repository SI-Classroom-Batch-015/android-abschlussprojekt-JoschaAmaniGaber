package com.example.rickandmortyguide.ui.introscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.rickandmortyguide.adapter.CharacterAdapter
import com.example.rickandmortyguide.data.remote.ApiStatus
import com.example.rickandmortyguide.databinding.FragmentIntroScreenBinding
import com.example.rickandmortyguide.ui.MainViewModel
import okhttp3.internal.wait

class IntroScreenFragment : Fragment() {

    private lateinit var binding: FragmentIntroScreenBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            fabDownload.setOnClickListener {

                viewModel.viewModelScope
                viewModel.loading.observe(
                    viewLifecycleOwner
                ) {
                    when (it) {
                        ApiStatus.LOADING -> {
                            binding.apply {
                                noDataAnim.visibility = View.GONE
                                rickAnim.visibility = View.VISIBLE
                                rickAnim.animation.start()
                            }
                        }

                        ApiStatus.DONE -> {
                            binding.apply {
                                noDataAnim.visibility = View.GONE
                                rickAnim.animation.duration = 888
                                rickAnim.visibility = View.GONE
                                rickAnim.animation.cancel()
                                val navController = Navigation.findNavController(root)
                                val direction = IntroScreenFragmentDirections.toCharacterFragment()
                                navController.navigate(direction)

                            }
                        }

                        null, ApiStatus.ERROR -> {
                            binding.apply {
                                rickAnim.visibility = View.VISIBLE
                                noDataAnim.visibility = View.GONE
                                rickAnim.animation.duration = 2222
                                rickAnim.animation.cancel()
                                rickAnim.visibility = View.GONE
                                noDataAnim.visibility = View.VISIBLE
                                noDataAnim.animation.start()
                            }
                        }
                    }
                }

                val navController = root.findNavController()
                val direction = IntroScreenFragmentDirections.toCharacterFragment()
                navController.navigate(direction)
            }
        }
    }
}