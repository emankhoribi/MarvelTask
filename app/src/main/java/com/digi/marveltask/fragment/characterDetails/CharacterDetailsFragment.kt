package com.digi.marveltask.fragment.characterDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.digi.domain.entity.comicapi.ComicResult
import com.digi.marveltask.R
import com.digi.marveltask.databinding.FragmentCharacterDetailsBinding
import com.digi.marveltask.databinding.FragmentCharactersBinding
import com.digi.marveltask.fragment.characterDetails.adapter.ComicsAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() , ComicsAdapter.RecyclerViewEvent{


    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var binding : FragmentCharacterDetailsBinding
    private val comicsAdapter = ComicsAdapter(this)
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
        val image = (character.thumbnail?.path.plus(".").plus(character.thumbnail?.extension)).replace("http", "https")
        Picasso.get().load(image).fit().into(binding.characterIv)
        binding.titleDetTv.text = character.name
        binding.descDetTv.text = character.description
        binding.comicsRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.comicsRv.adapter = comicsAdapter


        viewModel.getComics(character.id)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.comicsFLow.collect {
                    comicsAdapter.submitList(it?.data?.results)

                }
            }
        }


    }

    override fun onItemClick(comic: ComicResult) {
        TODO("Not yet implemented")
    }


}