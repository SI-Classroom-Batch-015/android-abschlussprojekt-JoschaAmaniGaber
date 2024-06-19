package com.example.rickandmortyguide.ui.start

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
import com.example.rickandmortyguide.ui.MainViewModel

class StartFragment : Fragment() {

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
            mcRick.visibility = View.INVISIBLE
            ivRick.isClickable = false
            button.visibility = View.VISIBLE
            lottieRick.visibility = View.GONE
            lottiePopcorn.visibility = View.GONE
            lottiePanda.visibility = View.GONE
            lottiePanda.playAnimation()
        }

        viewModel.loading.observe(viewLifecycleOwner) {

            when (it) {
                ApiStatus.LOADINGCHARACTERS -> binding.apply {
                    lottieRick.visibility = View.VISIBLE
                    lottieRick.playAnimation()
                    button.visibility = View.GONE
                }

                ApiStatus.LOADINGLOCATION -> binding.apply {
                    lottiePanda.visibility = View.VISIBLE
                    lottiePanda.playAnimation()
                }

                ApiStatus.CHARACTERSDONE -> binding.apply {
                    lottieRick.visibility = View.GONE
                    mcRick.visibility = View.VISIBLE
                    ivRick.isClickable = true
                    toast = Toast.makeText(
                        requireContext(),
                        "All Characters Loaded",
                        Toast.LENGTH_SHORT)
                    toast?.show()
                }

                ApiStatus.LOCATIONSDONE -> binding.apply {
                    lottiePopcorn.visibility = View.VISIBLE
                    lottiePopcorn.playAnimation()
                    lottieRick.pauseAnimation()
                    toast = Toast.makeText(
                        requireContext(),
                        "All Locations Loaded",
                        Toast.LENGTH_SHORT)
                    toast?.show()
                }

                ApiStatus.ERROR -> binding.apply {
                    lottiePopcorn.visibility = View.GONE
                    lottiePanda.pauseAnimation()
                    lottieRick.visibility = View.GONE
                    toast = Toast.makeText(
                        requireContext(),
                        "Could not load Data.",
                        Toast.LENGTH_SHORT)
                    toast?.show()
                }

//                ApiStatus.LOADINGEPISODES -> binding.apply {
//                    lottieStar.visibility = View.VISIBLE
//                    lottieStar.playAnimation()
//                }
//                ApiStatus.EPISODESDONE -> binding.apply {
//                    lottieStar.pauseAnimation()
//                    toast = Toast.makeText(
//                        requireContext(),
//                        "All Episodes Loaded",
//                        Toast.LENGTH_SHORT)
//                    toast?.show()
//                }
            }
        }

        startAnimBtBg()

        binding.apply {
            button.setOnClickListener {
                viewModel.loadDatabases()
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

    private fun startAnimBtBg() {
        val animLogoBt: AnimationDrawable = binding.ivRick.background as AnimationDrawable
        val animBg: AnimationDrawable = binding.root.background as AnimationDrawable
        val startAnims: List<AnimationDrawable> = listOf(animLogoBt, animBg)
        var anim: AnimationDrawable
        repeat(startAnims.size) {
            anim = startAnims[it]
            anim.setEnterFadeDuration(3333)
            anim.setExitFadeDuration(3333)
            anim.start()
        }
    }
}