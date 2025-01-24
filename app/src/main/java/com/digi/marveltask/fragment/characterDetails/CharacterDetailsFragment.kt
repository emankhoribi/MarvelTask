package com.digi.marveltask.fragment.characterDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.digi.domain.entity.comicapi.ComicResult
import com.digi.domain.entity.comicapi.Thumbnail
import com.digi.marveltask.databinding.FragmentCharacterDetailsBinding
import com.digi.marveltask.fragment.characterDetails.adapter.ComicsAdapter
import com.digi.marveltask.util.Constants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment(), ComicsAdapter.RecyclerViewEvent {


    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var binding: FragmentCharacterDetailsBinding
    private val comicsAdapter = ComicsAdapter(this)
    private val seriesAdapter = ComicsAdapter(this)
    private val storiesAdapter = ComicsAdapter(this)
    private val eventsAdapter = ComicsAdapter(this)
    private var thumbnails: ArrayList<Thumbnail> = arrayListOf()
    val characterData: CharacterDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val character = characterData.characterDetails
        val image = (character.thumbnail?.path.plus(".")
            .plus(character.thumbnail?.extension)).replace("http", "https")
        Picasso.get().load(image).fit().into(binding.characterIv)
        binding.titleDetTv.text = character.name
        binding.descDetTv.text = character.description
        binding.comicsRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.comicsRv.adapter = comicsAdapter

        binding.seriesRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.seriesRv.adapter = seriesAdapter

        binding.storiesRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.storiesRv.adapter = storiesAdapter

        binding.eventsRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.eventsRv.adapter = eventsAdapter


        viewModel.getComics(character.id)
        viewModel.getSeries(character.id)
        viewModel.getEvents(character.id)
        viewModel.getStories(character.id)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.comicsFLow.collect {
                        comicsAdapter.submitList(it?.data?.results)
                    }
                }

                launch {
                    viewModel.storiesFLow.collect {
                        storiesAdapter.submitList(it?.data?.results)
                        }
                }

                launch {
                    viewModel.seriesFlow.collect {
                        seriesAdapter.submitList(it?.data?.results)
                       }
                }

                launch {
                    viewModel.eventsFLow.collect {
                        eventsAdapter.submitList(it?.data?.results)

                    }
                }

            }
        }


    }

    override fun onItemClick(comicList: MutableList<ComicResult>, position: Int) {
        thumbnails.clear()
        for (comics in comicList) {
            if (comics.thumbnail != null)
                thumbnails.add(comics.thumbnail!!)
        }

        if (thumbnails.isNotEmpty()) {
            val directions =
                CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToImageSliderBottomSheetFragment(
                    thumbnails.toTypedArray(),
                    position
                )
            findNavController().navigate(directions)
        }
    }


}