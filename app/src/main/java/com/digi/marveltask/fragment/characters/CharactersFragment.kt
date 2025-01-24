package com.digi.marveltask.fragment.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.TransitionInflater
import androidx.transition.Visibility
import com.digi.domain.entity.Result
import com.digi.marveltask.R
import com.digi.marveltask.databinding.FragmentCharactersBinding
import com.digi.marveltask.fragment.characters.adapter.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class CharactersFragment : Fragment(), CharactersAdapter.RecyclerViewEvent {

    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var binding: FragmentCharactersBinding
    private var charactersAdapter = CharactersAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.characterRv.layoutManager = LinearLayoutManager(requireContext())
        binding.characterRv.adapter = charactersAdapter


        getCharacters()


        binding.searchIv.setOnClickListener {
            if (binding.searchEt.isVisible) {
                binding.searchEt.visibility = View.GONE
                binding.searchIv.setImageResource(R.drawable.ic_search)
                binding.searchEt.text.clear()
                binding.logoIv.visibility = View.VISIBLE
            } else {
                binding.searchEt.visibility = View.VISIBLE
                binding.searchIv.setImageResource(R.drawable.ic_search_off)
                binding.logoIv.visibility = View.INVISIBLE
            }
        }

        binding.searchEt.doOnTextChanged { text, start, before, count ->

            getSearchedCharacters(text.toString())
        }

        lifecycleScope.launch {

            charactersAdapter.loadStateFlow.
            distinctUntilChangedBy {
                it.refresh
            }.collect {
                if( it.refresh is LoadState.Loading){
                    binding.progressBar.visibility = View.VISIBLE
                }else{
                    binding.progressBar.visibility = View.GONE
                    if (it.refresh is LoadState.Error){
                        Toast.makeText(requireContext(), (it.refresh as LoadState.Error).error.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }




            }
        }
    }

    private fun getCharacters() {
        lifecycleScope.launch {
            viewModel.getCharacterList().collectLatest {
                charactersAdapter.submitData(it)

            }
        }


    }

    private fun getSearchedCharacters(nameStartsWith: String) {
        lifecycleScope.launch {
            viewModel.getSearchedCharacterList(nameStartsWith).collect {
                charactersAdapter.submitData(it)

            }
        }
    }

    override fun onItemClick(character: Result) {
        val directions =
            CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(
                character
            )
        findNavController().navigate(directions)
    }

}